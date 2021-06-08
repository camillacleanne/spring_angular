package br.org.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.blogpessoal.model.Usuario;

/**
 * User Details é uma Classe que contem todos os métodos que 
 * podemos chamar no User Details Service e em outras camadas.
 * 
 */

public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;

	/* Collection com todos os direitos de acesso do Usuário (Rols => Permissões) 
	 Não usaremos nesta implementação*/

	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();		
	}

	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/* Recupera a senha do Token */
	@Override
	public String getPassword() {
		return password;
	}

	/* Recupera o usuário do Token */
	@Override
	public String getUsername() {

		return userName;
	}

	/* Informa se a conta do usuário expirou */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* Informa se a conta do usuário está bloqueada */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* Informa se a senha do usuário expirou */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* Informa se a conta do usuário está ativa */
	@Override
	public boolean isEnabled() {
		return true;
	}
} 
