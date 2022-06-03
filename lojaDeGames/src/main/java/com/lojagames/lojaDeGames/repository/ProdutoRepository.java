package com.lojagames.lojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojagames.lojaDeGames.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByNomeJogoContainingIgnoreCase(String nomeJogo);
	public List<Produto> findAllByModoDeJogoContainingIgnoreCase(String modoDeJogo);
	public List<Produto> findByPrecoLessThanEqual(Double preco);
	public List<Produto> findByPromocaoTrue();
	
}
