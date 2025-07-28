package com.citasmedicas.democitas.paciente;

import com.citasmedicas.democitas.direccion.DatosDireccion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
		@NotNull Long id, 
	    String nombre, 
	    String telefono, 
	    @Valid DatosDireccion direccion) {

}
