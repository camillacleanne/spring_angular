package br.org.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.model.UsuarioLogin;
import br.org.generation.blogpessoal.repository.UsuarioRepository;

/**
	 * Classe de Serviço (@Service)
	 * 
	 * Implementa as regras de negócio da API
	 * 
	 * Como implementamos um serviço de Login, Cadastro e Alteração de dados 
	 * dos nossos usuários, de acordo com as Regras de negócio da camada de 
	 * segurança da nossa Api, precisamos criar um pacote Service (Usuario Service),
	 * onde iremos criar uma classe Serviço, cujos métodos serão chamados na respectiva
	 * classe Controller no pacote Controller.
	 * 
	 */

@Service
public class UsuarioService {

	/* Injeção de dependência (UsuarioRepository) */
	@Autowired
	private UsuarioRepository usuarioRepository;

public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
	
		/* Verifica se o usuário existe*/
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return null;
		
		/* Verifica se o email existe*/
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return null;
		
		/* Criprtografa a senha*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());

		/* Atualiza a senha no objeto usuario, ou seja,
		substitui a senha digitada pela senha criptografada */
		usuario.setSenha(senhaEncoder);

		return Optional.of(usuarioRepository.save(usuario));
	}

	/**
	 * O Método Put, embora permita alterar todos os dados do usuário,
	 * será utilizado principalmente para alterar a senha e o email
	 * 
	 * Importante: No front deve-se permitir apenas a alteração da senha e/ou email
	 * Os demais dados devem ser passados iguais aos salvos no Banco de Dados 
	 */

	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		/* Criprtografa a senha*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
	
		/* Atualiza a senha no objeto usuario, ou seja,
		substitui a senha digitada pela senha criptografada */
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(usuarioRepository.save(usuario));

	}
	
	/**
	 * Método para efetuar login no sistema
	 */
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(user.get().getUsuario());

		// Verifia se o usuário existe
		if (usuario.isPresent()) {

			// Verifica se a senha digitada está correta (Senha já criptografada)
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				// Gera o Token
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				// Grava os parâmetros no objeto user do tipo UsuarioLogin
				user.get().setToken(authHeader);				
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());

				return user;

			}
		}
		return null;
	}

}
