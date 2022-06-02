package com.farmacia.produtosFarmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.produtosFarmacia.model.Categoria;
import com.farmacia.produtosFarmacia.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired //transferindo dependência --> repository
	private CategoriaRepository repository;
	
	@GetMapping // 1 - Get (mostrar tudo)
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") // 2 - Get por ID (mostrar um único)
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeCategoria}") //3 - Get por Nome (mostrar aqueles que tem este nome)
	public ResponseEntity<List<Categoria>> getByNomeCategoria(@PathVariable String nomeCategoria){
		return ResponseEntity.ok(repository.findAllByNomeCategoriaContainingIgnoreCase(nomeCategoria));
	}
	
	@PostMapping //4 - Post (criar categoria)
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping //5 - Put (atualizar categoria
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}") //6 - Delete (apagar uma categoria por id)
	public void delete(@PathVariable Long id){
		repository.deleteById(id);
	}
	
}
