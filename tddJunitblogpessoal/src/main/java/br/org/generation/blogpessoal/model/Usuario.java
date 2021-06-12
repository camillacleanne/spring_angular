package br.org.generation.blogpessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O atributo nome é Obrigatório!")
	@Size(min = 5, max = 100)
	private String nome;
	
	@NotNull(message = "O atributo usuário é Obrigatório!")
	@Size(min = 5, max = 100)
	private String usuario;
	
	@NotNull(message = "O atributo senha é Obrigatório!")
	@Size(min = 8)
	private String senha;
	
	@NotNull(message = "O atributo email é Obrigatório!")
	@Email
	private String email;
	
	@NotNull(message = "O atributo admin é Obrigatório!")
	private boolean admin;

	/** Construtores Criados para realização de testes */

	/** Construtor Vazio - Cria um Objeto vazio */
	public Usuario() {
	}

	/** Construtor com os atributos - Cria um Objeto com dados */
	public Usuario(long id, String nome, String usuario, String senha, String email, boolean admin) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
		this.admin = admin;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
