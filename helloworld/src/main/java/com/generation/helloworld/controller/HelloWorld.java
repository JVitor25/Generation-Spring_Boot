package com.generation.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que a classe abaixo é classe controladora
@RequestMapping("/hello") //serve para a construção dos endpoints da api
public class HelloWorld {
	
	@GetMapping
	public String hello() {
		return "Orientação ao Futuro; Responsabilidade Pessoal; Mentalidade de Crescimento; Persistência; Trabalho em equipe; Atenção aos Detalhes; Proatividade; e Comunicação.";
	}
}
