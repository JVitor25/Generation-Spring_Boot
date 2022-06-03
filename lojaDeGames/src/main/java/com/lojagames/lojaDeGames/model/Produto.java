package com.lojagames.lojaDeGames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nomeJogo;
	
	private Double preco;
	
	private int classificacao;//3; 7; 12; 16; e 18. (idade)
	
	private String modoDeJogo;//Competitivo, Cooperativo, Multiplayer, ou Soloplayer.
	
	private Boolean promocao;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	//Get & Set
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeJogo() {
		return nomeJogo;
	}

	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setValor(Double preco) {
		this.preco = preco;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}

	public String getModoDeJogo() {
		return modoDeJogo;
	}

	public void setModoDeJogo(String modoDeJogo) {
		this.modoDeJogo = modoDeJogo;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
