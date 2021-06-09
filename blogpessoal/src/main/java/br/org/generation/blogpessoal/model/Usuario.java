package br.org.generation.blogpessoal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String nome;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String usuario;
	
	@NotNull
	@Size(min = 8)
	private String senha;
	
	@NotNull
	@Email
	private String email;
	
	/* Atributo extra para testar a idade do usuario */
	private LocalDate dataAniversario;
	
	@NotNull
	private boolean admin;
	
	/** 
	 * Auto Relacionamento
	 * 
	 * Semelhante ao relacionamento entre 2 tabelas. Diferenças:
	 * 
	 * @JoinColumn(name="supervisor") -> Define o atributo que fará 
	 * o papel da Chave estrangeira
	 * 
	 * cascade = CascadeType.REMOVE -> Alterações somente serão propagadas
	 * em caso de remoção (Delete) do registro
	 * 
	 * A anottation @JsonIgnoreProperties(value = {"equipe", "supervisor"}) -> impede
	 * o looping infinito na exibição do JSON. Como estamos trabalhando com um 
	 * auto relacionamento, precisamos desabilitar os dois atributos (equipe e supervisor)
	 * em ambos os lados da relação.
	 * 
	 * Não esqueça de criar os métodos Get e Set dos Atributos equipe e supervisor.
	 * 
	 */

	@ManyToOne
    @JoinColumn(name="supervisor")
	@JsonIgnoreProperties(value = {"equipe", "supervisor"})
    private Usuario supervisor;

    @OneToMany(mappedBy="supervisor", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = {"equipe", "supervisor"})
    private List<Usuario> equipe;

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

	public LocalDate getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(LocalDate dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}

	public List<Usuario> getEquipe() {
		return equipe;
	}

	public void setEquipe(List<Usuario> equipe) {
		this.equipe = equipe;
	}

}
