package com.citasmedicas.democitas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.democitas.medico.DatosActualizacionMedico;
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
	public void registrar(@RequestBody @Valid DatosRegistrosMedicos datos) {
		
		repository.save(new Medico(datos));
		
	}
	
	@GetMapping
	public Page<DatosListaMedico> listar(@PageableDefault(size=10, sort= {"nombre"})  Pageable paginacion){
		return repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
	}
	
	@Transactional
	@PutMapping
	public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
		var medico = repository.getReferenceById(datos.id());
		medico.actualizarInformaciones(datos);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Long id ) {
		var medico = repository.getReferenceById(id);
		medico.eliminar();
	}


}
