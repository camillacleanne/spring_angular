package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.model.UsuarioLogin;
import br.org.generation.blogpessoal.repository.UsuarioRepository;
import br.org.generation.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	/* Injeção de dependência - Classe Repositorio (UsuarioRepository) */
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/* Injeção de dependência - Classe Serviço (UsuarioService) */
	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

    @GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id){
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}

	/** 
	 * Endpoint de autenticação (Login)
	 * 
	 * Executa o método Logar da classe de serviço e verifica se tudo deu certo
	 */

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

		/** 
	 * Endpoint de cadastro
	 * 
	 * Executa o método CadastarUsuario da classe de serviço 
	 * e verifica se tudo deu certo
	 * 
	 */

	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> Post(@RequestBody Usuario usuario) {
		
		Optional<Usuario> user = usuarioService.CadastrarUsuario(usuario);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(user);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
		/** 
	 * Endpoint de update
	 * 
	 * Executa o método AtualizarUsuario da classe de serviço 
	 * e verifica se tudo deu certo
	 * 
	 */

	@PutMapping("/alterar")
	public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario){
		Optional<Usuario> user = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
