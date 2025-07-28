package com.citasmedicas.democitas.medico;

import com.citasmedicas.democitas.direccion.DatosDireccion;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionMedico(
		@NotNull
		Long id,		
		String telefono,
		String nombre,
		DatosDireccion direccion) {
	
	

}
