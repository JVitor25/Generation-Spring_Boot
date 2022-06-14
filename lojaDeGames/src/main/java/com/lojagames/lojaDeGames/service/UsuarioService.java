package com.lojagames.lojaDeGames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lojagames.lojaDeGames.model.UserLogin;
import com.lojagames.lojaDeGames.model.Usuario;
import com.lojagames.lojaDeGames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	/*
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
	
		if(repository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(repository.save(usuario));
	}
	
	public Usuario 
	
	
	public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> user){
		
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(compararSenhas(user.get().getSenha(),usuario.get().getSenha())) {
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setToken(gerarBasicToken(user.get().getUsuario(), user.get().getSenha()));
				
				return user;
			}
		}
		
		return Optional.empty();
	}
	
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		if(repository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = repository.findByUsuario(usuario.getUsuario());
			
			if(buscaUsuario.isPresent()) {
				if(buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.of(repository.save(usuario));
		}
		
		return Optional.empty();
	}
	
	
	
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
	/*private String gerarBasicToken(String usuario, String password) {
		String tokenBase = usuario + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}*/
	
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()){
			if(encoder.matches(user.get().getSenha(),usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}
		return null;
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		if(repository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = repository.findByUsuario(usuario.getUsuario());
			
			if(buscaUsuario.isPresent()) {
				if(buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}
			
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(repository.save(usuario));
		}
		
		return Optional.empty();
	}
}
