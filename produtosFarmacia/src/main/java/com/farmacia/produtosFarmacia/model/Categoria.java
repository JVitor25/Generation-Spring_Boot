package com.farmacia.produtosFarmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nomeCategoria;

	@OneToMany(mappedBy="categoria", cascade = CascadeType.ALL)//"categoria" refere-se ao nome do objetvo(public class ...)
	/* se for .ALL todos produtos vão ser atualizados.
	 * se for .REMOVE todos produtos vão ser removidos (quando editarmos esse objeto). 
	 */
	@JsonIgnoreProperties("categoria")//"categoria" refere-se à tabela criada no produto. Para envitar recursividade.
	private List<Produto> produto;

	
	//Get & Set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
}
