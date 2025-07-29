package com.citasmedicas.democitas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.democitas.usuario.DatosAutenticacion;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticationController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
		
		var token = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
		var autenticacion = manager.authenticate(token);
		
		return ResponseEntity.ok().build();
	}
	

}
