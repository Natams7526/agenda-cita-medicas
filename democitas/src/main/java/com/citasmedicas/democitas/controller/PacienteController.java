package com.citasmedicas.democitas.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.democitas.paciente.DatosActualizacionPaciente;
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
	    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) { 
	        repository.save(new Paciente(datos)); 
	    } 
	    
	    @GetMapping 
	    public Page<DatosListaPaciente> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) { 
	        return repository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new); 
	    }
	    
	    @PutMapping 
	    @Transactional 
	    public void atualizar(@RequestBody @Valid DatosActualizacionPaciente datos) { 
	        var paciente = repository.getReferenceById(datos.id()); 
	        paciente.actualizarInformacion(datos); 
	    } 

	    @DeleteMapping("/{id}") 
	    @Transactional 
	    public void eliminar(@PathVariable Long id) { 
	        var paciente = repository.getReferenceById(id); 
	        paciente.desactivar(); 
	    } 
	
}
