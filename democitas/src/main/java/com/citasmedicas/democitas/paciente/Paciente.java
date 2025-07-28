package com.citasmedicas.democitas.paciente;

import com.citasmedicas.democitas.direccion.Direccion;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@EqualsAndHashCode(of = "id") 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity(name = "Paciente") 
@Table(name = "pacientes") 
public class Paciente { 

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;     
    private Boolean activo;
    private String nombre; 
    private String email; 
    private String documentoIdentidad; 
    private String telefono; 

    @Embedded 
    private Direccion direccion; 

    public Paciente(DatosRegistroPaciente datos) { 
        this.nombre = datos.nombre(); 
        this.activo = true;
        this.email = datos.email(); 
        this.telefono = datos.telefono(); 
        this.documentoIdentidad = datos.documento_identidad(); 
        this.direccion = new Direccion(datos.direccion()); 
    }

	public void actualizarInformacion(@Valid DatosActualizacionPaciente datos) {
		if (datos.nombre() != null) 
	        this.nombre = datos.nombre(); 
	    
	    if (datos.telefono() != null) 
	        this.telefono = datos.telefono(); 

	    if (datos.direccion() != null) 
	        direccion. actualizarDireccion(datos.direccion());
		
	} 
	
	public void desactivar() { 
	    this.activo = false; 
	} 

} 
