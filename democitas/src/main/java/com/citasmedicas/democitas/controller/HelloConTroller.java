package com.citasmedicas.democitas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/hello")
public class HelloConTroller {

	
	@GetMapping
	public String getMethodName() {
		return "Hello Natha :) ";
	}
	
	
	

}
