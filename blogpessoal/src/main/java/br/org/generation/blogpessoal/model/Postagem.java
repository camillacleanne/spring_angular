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
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Atributo Obrigatório")
	@Size(min = 5, max = 100, message = "O atributo deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo;

	@NotNull(message = "Atributo Obrigatório")
	@Size(min = 10, max = 500, message = "O atributo deve conter no mínimo 10 e no máximo 500 caracteres")
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	/**
	 * O Atributo curtidas irá armazenar no Banco de Dados o total de curtidas
	 * que a Postagem teve.
	 * 
	 * A anottation @PositiveOrZero evita numeros negativos
	 * 
	 * Não esqueça de criar os Métodos Get e Set
	 * 
	 * A implemntação dos métodos Curtir e Descurtir estão na classe de serviço
	 * PostagemService
	 */

	@PositiveOrZero
	private int curtidas;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
		
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

	/**
	 * Métodos Get e Set do atributo curtidas
	 */
	public int getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(int curtidas) {
		this.curtidas = curtidas;
	}

}
