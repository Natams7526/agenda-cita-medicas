package com.citasmedicas.democitas.medico;

import com.citasmedicas.democitas.direccion.Direccion;

public record DatosDetalleMedico(
		Long id,
		String nombre,
		String email,
		String documento,
		String telefono,
		Especialidad especialidad,
		Direccion direccion
	) {
	
	public DatosDetalleMedico(Medico medico) {
		this(
				medico.getId(),
				medico.getNombre(),
				medico.getEmail(),
				medico.getDocumento(),
				medico.getTelefono(),
				medico.getEspecialidad(),
				medico.getDireccion()
		);
	}

}
