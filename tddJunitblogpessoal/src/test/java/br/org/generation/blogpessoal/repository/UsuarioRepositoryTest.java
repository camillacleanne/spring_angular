package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	/** Injeta o Repositório - UsuarioRepository */
    @Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {

		/** Instancia 4 objetos do tipo Usuario e grava no Banco de Dados
		 *  se o usuario for diferente de null
		 */

		Usuario usuario = new Usuario(0, "João da Silva", "jsilva", "13465278", "joao@email.com.br", true);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
		
		usuario = new Usuario(0, "Manuel da Silva", "msilva", "13465278", "manuel@email.com.br", true);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
		
		usuario = new Usuario(0, "Frederico da Silva", "fsilva", "13465278", "frederico@email.com.br", true);
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);

        usuario = new Usuario(0, "Paulo Antunes", "paulo", "13465278", "paulo@email.com.br", true);
        if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
            usuarioRepository.save(usuario);
	}
	
	@Test
	@DisplayName("💾 Retorna o nome")
	public void findByNomeRetornaNome() throws Exception {

		Usuario usuario = usuarioRepository.findFirstByNome("João da Silva");
		assertTrue(usuario.getNome().equals("João da Silva"));
	}
	
    
	@Test
	@DisplayName("💾 Retorna 3 usuarios")
	public void findAllByUsuarioContainingIgnoreCaseRetornaTresUsuarios() {

		/** Caso a tabela esteja com muitos dados cadastrados, este teste poderá falhar */

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByUsuarioContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		
		/** Apaga todos os dados */
		
		usuarioRepository.deleteAll();
	}

}
