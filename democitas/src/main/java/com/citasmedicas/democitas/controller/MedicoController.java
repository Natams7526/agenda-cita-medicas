package com.citasmedicas.democitas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.citasmedicas.democitas.medico.DatosActualizacionMedico;
import com.citasmedicas.democitas.medico.DatosDetalleMedico;
import com.citasmedicas.democitas.medico.DatosListaMedico;
import com.citasmedicas.democitas.medico.DatosRegistrosMedicos;
import com.citasmedicas.democitas.medico.Medico;
import com.citasmedicas.democitas.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@Transactional
	@PostMapping
	public ResponseEntity registrar(@RequestBody @Valid DatosRegistrosMedicos datos, UriComponentsBuilder uriComponentsBuilder) {
		var medico = new Medico(datos);
		repository.save(medico);
		
		var  uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
		
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size=10, sort= {"nombre"})  Pageable paginacion){
		var page = repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
		
		return ResponseEntity.ok(page);
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
		var medico = repository.getReferenceById(datos.id());
		medico.actualizarInformaciones(datos);
		
		return ResponseEntity.ok(new DatosDetalleMedico(medico));
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity eliminar(@PathVariable("id") Long id ) {
		var medico = repository.getReferenceById(id);
		medico.eliminar();
		
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity detallar(@PathVariable("id") Long id ) {
		var medico = repository.getReferenceById(id);;
		
		return ResponseEntity.ok(new DatosDetalleMedico(medico));
	}


}
