package com.citasmedicas.democitas.medico;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import com.citasmedicas.democitas.direccion.Direccion;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private Boolean activo;
	private String nombre;	
	private String email;	
	private String telefono;
	private String documento;
	
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	
	@Embedded
	private Direccion direccion; 
	
	public Medico(DatosRegistrosMedicos datos) {
		this.id = null;
		this.activo = true;
		this.nombre = datos.nombre();
		this.email = datos.email();
		this.telefono = datos.telefono();
		this.documento = datos.documento();
		this.especialidad = datos.especialidad();
		this.direccion = new Direccion(datos.direccion());
		
	}

	public void actualizarInformaciones(@Valid DatosActualizacionMedico datos) {
		if (datos.nombre() != null) {
			this.nombre = datos.nombre();
			}
		if (datos.telefono() != null) {
			this.telefono = datos.telefono();
			}
		if (datos.direccion() != null) {
			this.direccion.actualizarDireccion(datos.direccion()); 
			}
		
	}

	public void eliminar() {
		this.activo = false;
		
	}
	

}
    