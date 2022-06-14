package org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*Indica que a classe atual é do tipo SpringBootTest,
 * e caso a porta 8080 esteja sendo ocupada, ele 
 * vai utilizar outra*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)


/*Indica que o ciclo de vida de Teste será por classe
 * (um único ciclo de vida enquanto o teste está em 
 * execução)*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

/*indica em qual ordem os teste serão executados 
 *(insedidos na classe Order)*/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar um Usuário")
	public void deveCriarUmUsuario() {
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario (
				0L,"Paulo Antunes","https://i.imgur.com/h4t8loa.jpg","paulo@gmail.com","12345678"));
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(
				"/usuarios/cadastrar", HttpMethod.POST,requisicao,Usuario.class);
		
		assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(),resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getFoto(),resposta.getBody().getFoto());
		assertEquals(requisicao.getBody().getUsuario(),resposta.getBody().getUsuario());
	}
	
	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {
		
		usuarioService.cadastrarUsuario(new Usuario(
				0L,"Maria da Silva","https://i.imgur.com/h4t8loa.jpg","maria@gmail.com","12345678"));
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(
				0L,"Maria da Silva","https://i.imgur.com/h4t8loa.jpg","maria@gmail.com","12345678"));
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(
				"/usuarios/cadastrar", HttpMethod.POST,requisicao,Usuario.class);
		
		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());	
	}
	
	@Test
	@Order(3)
	@DisplayName("Alterar um Usuário")
	public void deveAtualizarUmUsuario() {
		
		Optional<Usuario> usuarioCreate = usuarioService.cadastrarUsuario(new Usuario(
				0L,"Juliana Andreia","https://i.imgur.com/h4t8loa.jpg","juliana_andreia@gmail.com","12345678"));
		
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),"Juliana Andreia Ramos",
				"https://i.imgur.com/h4t8loa.jpg","juliana_andreia@gmail.com","12345678");
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> resposta = testRestTemplate
				.withBasicAuth("root","root")
				.exchange("/usuarios/atualizar",HttpMethod.PUT,requisicao,Usuario.class);
		
		assertEquals(HttpStatus.OK,resposta.getStatusCodeValue());
		assertEquals(usuarioUpdate.getNome(),resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getFoto(),resposta.getBody().getFoto());
		assertEquals(usuarioUpdate.getUsuario(),resposta.getBody().getUsuario());
	}
	
	@Test
	@Order(4)
	@DisplayName("Listar todos Usuários")
	public void deveMostrarTodosUsuarios() {
		
		usuarioService.cadastrarUsuario(new Usuario(
				0L,"Sabrina Sanches","https://i.imgur.com/h4t8loa.jpg","sabrina_sanches@gmail.com","sabrina123"));
		usuarioService.cadastrarUsuario(new Usuario(
				0L,"Ricardo Marques","https://i.imgur.com/h4t8loa.jpg","ricado_marques@gmail.com","ricardo123"));
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root","root")
				.exchange("/usuarios/all",HttpMethod.GET,null,String.class);
		
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
	}
}

