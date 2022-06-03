package com.lojagames.lojaDeGames.controller;

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

import com.lojagames.lojaDeGames.model.Produto;
import com.lojagames.lojaDeGames.repository.ProdutoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	private ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Produto> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeProduto}")
	private ResponseEntity<List<Produto>> getByNomeProduto(@RequestBody String nomeProduto){
		return ResponseEntity.ok(repository.findAllByNomeJogoContainingIgnoreCase(nomeProduto));
	}
	
	
	@GetMapping("/modo/{modoDeJogo}")
	private ResponseEntity<List<Produto>> getByModoDeJogo(@RequestBody String modoDeJogo){
		return ResponseEntity.ok(repository.findAllByModoDeJogoContainingIgnoreCase(modoDeJogo));
	}
	
	
	@GetMapping("/ate/{preco}")
	private ResponseEntity<List<Produto>> getByPrecoMenorIgual(@RequestBody Double preco){
		return ResponseEntity.ok(repository.findByPrecoLessThanEqual(preco));
	}
	
	@GetMapping("/promocao")
	private ResponseEntity<List<Produto>> getByPromocao(){
		return ResponseEntity.ok(repository.findByPromocaoTrue());
	}
	
	@PostMapping
	private ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	private ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	private void delete(@PathVariable Long id){
		repository.deleteById(id);
	}
}
