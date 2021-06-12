package org.generation.blogPessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private @Autowired UserDetailsServiceImpl service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/** 
		 * Autenticação em memória: Cria um Usuário e Senha para testes,
		 * disoensando a necessidade de cadastrar um usuário no Banco de Dados.
		 * 
		 * Não utilizar este recurso em abiente de produção.
		 */
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(passwordEncoder()
			.encode("admin"))
			.authorities("ROLE_ADMIN");
		
		auth.userDetailsService(service);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers("/usuarios/logar").permitAll()
 		.anyRequest().authenticated()
 		.and().httpBasic()
 		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
 		.and().cors()
 		.and().csrf().disable();
	}
	
	
}
