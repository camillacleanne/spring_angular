package br.org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temas")
public class Tema {
    
    @Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "Atributo obrigatório")
	private String descricao;
	
	/**
	 * O Atributo qtdTema irá armazenar o total de postagens
	 * associadas a este tema.
	 * 
	 * A anottation @Transient impede que este atributo seja criado na tabela
	 * de temas do Banco de dados, ou seja, ele só existe nesta Classe
	 * 
	 * Não esqueça de criar os Métodos Get e Set
	 * 
	 * A implemntação do método trendTopics está na classe de serviço
	 * TemaService
	 */

	@Transient
	private int qtdTema;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	/**
	 * Métodos Get e Set do atributo qtdTema
	 */

	public int getQtdTema() {
		return qtdTema;
	}

	public void setQtdTema(int qtdTema) {
		this.qtdTema = qtdTema;
	}
	
}
