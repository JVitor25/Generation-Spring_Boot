package org.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
// versão bombada do NotNull:NotBlank --> import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/* Indica para o Spring que o objeto criado
vai se tornar uma tabela no banco de dados. */
@Entity

// Indica o nome dessa tabela.
@Table(name="tb_postagens")
public class Postagem {
	// Indica que o campo ID será uma chave primária.
	@Id
	// Cria o AutoIncremente da Primary Key. 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Vai deixar o campo obrigatório. 
	@NotNull
	private String titulo;
	
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	// Get & Set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
