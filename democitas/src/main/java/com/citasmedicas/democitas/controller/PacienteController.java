package com.citasmedicas.democitas.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.citasmedicas.democitas.paciente.DatosActualizacionPaciente;
import com.citasmedicas.democitas.paciente.DatosDetallePaciente;
import com.citasmedicas.democitas.paciente.DatosListaPaciente;
import com.citasmedicas.democitas.paciente.DatosRegistroPaciente;
import com.citasmedicas.democitas.paciente.Paciente;
import com.citasmedicas.democitas.paciente.PacienteRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("pacientes") 
public class PacienteController { 

	    @Autowired 
	    private PacienteRepository repository; 

	    @PostMapping 
	    @Transactional 
	    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriComponentBuilder) { 
	        var paciente = new Paciente(datos);
	    	repository.save(paciente); 
	    	
	    	var uri = uriComponentBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
	    	return ResponseEntity.created(uri).body(new DatosDetallePaciente(paciente));
	    } 
	    
	    @GetMapping 
	    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) { 
	        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
	    	return  ResponseEntity.ok(page);
	        
	    }
	    
	    @PutMapping 
	    @Transactional 
	    public ResponseEntity atualizar(@RequestBody @Valid DatosActualizacionPaciente datos) { 
	        var paciente = repository.getReferenceById(datos.id()); 
	        paciente.actualizarInformacion(datos); 
	        
	        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
	    } 

	    @DeleteMapping("/{id}") 
	    @Transactional 
	    public ResponseEntity eliminar(@PathVariable Long id) { 
	        var paciente = repository.getReferenceById(id); 
	        paciente.desactivar(); 
	        
	        return ResponseEntity.noContent().build();
	    } 
	    
	    @GetMapping("/{id}")
	    public ResponseEntity detallar(@PathVariable Long id) {
	        var paciente = repository.getReferenceById(id);
	        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
	    }
	
}
