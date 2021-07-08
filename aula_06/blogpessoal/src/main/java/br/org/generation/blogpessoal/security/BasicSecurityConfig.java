package br.org.generation.blogpessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Camada Security - Implmenta a segurança da API
 * 
 * A Classe Basic Security Config é uma uma classe de configuração. 
 * Ela permite configurar o tipo de criptografia da senha, o tipo de
 * segurança (Basic, neste exemplo) e os endpoints que serão liberados
 * para o usuário acessar a API (Cadastrar e Login)
 * 
 */

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	/* Injeção de dependência (UserDetailsService) */
	@Autowired
	private UserDetailsService userDetailsService;

	/* Define que todas as autenticações serão do tipo userDetailsService */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/* Define a classe que fará o encode (Criptografia) da senha
	As Classes de encode são definidas na dependência commons-codec*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* Método responsável por configurar as requisições (Requests) da API */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * antMatchers().permitAll() -> Define os endpoints que serão utilizados
		 * sem autenticação (Login)
		 * 
		 * sessionCreationPolicy(SessionCreationPolicy.STATELESS) -> Informa que a nossa
		 * API é do tipo Stateless, ou seja, não guarda Sessões para o Cliente
		 * 
		 * cors() -> Cross Origins: Libera acessa  de outras origens, como o front-end
		 * 
		 * csrf().disabled() -> Desabilita o Token CSRF. Um token CSRF é um valor exclusivo, 
		 * secreto e imprevisível criado pelo aplicativo do lado do servidor e transmitido 
		 * ao cliente em uma requisição HTTP, para inibir a falsificação de solicitações. 
		 * Como utilizamos um Token de autenticação, não é necessário manter esta opção
		 * habilitada.
		 */

		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
}
