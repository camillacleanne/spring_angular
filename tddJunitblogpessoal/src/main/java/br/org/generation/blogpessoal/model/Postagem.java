package br.org.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "O atributo título é Obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo;

	@NotNull(message = "O atributo texto é Obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 500 caracteres")
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	/** Construtores Criados para realização de testes */

	/** Construtor Vazio - Cria um Objeto vazio */

	public Postagem() {
	}
	
	/** Construtor com atributo Tema - Teste da camada de Controller */

	public Postagem(long id, String titulo, String texto, Date data, Tema tema) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.data = data;
		this.tema = tema;
	}
	
	
	/** Construtor sem o atributo do tipo Tema
	 *  
	 *  Esta implementação simplifica os testes das camadas Model e 
	 *  Repository, por dispensar a necessidade de instanciar um
	 *  objeto do tipo Tema.
	 * 
	 */

	public Postagem(long id, String titulo, String texto, Date data) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.data = data;

	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
