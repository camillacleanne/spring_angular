package br.org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.TemaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostagemControllerTest {

	private Postagem postagem;
	private Postagem postagemUpdate;

	/*
	 * Criando um objeto do tipo TestRestTemplate para criar uma
	 * requisição http 
	 * */

    @Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private TemaRepository temaRepository;

	@BeforeAll
	public void start() throws ParseException {

		/**
		 * Antes de criar o teste, não esqueça de criar os métodos
		 * Construtores nas Models Tema e Postagem e Cadastrar um
		 * usuário e senha no Banco de Dados 
		 * 
		 * Para criarmos um Objeto do tipo Postagem para enviar na
		 * requisição do tipo Post ou do tipo Put, precisamos adicionar
		 * um objeto do tipo Tema.
		 * 
		 * No Postman, fazemos isso ao passar o tema_id na requisição,
		 * entretanto no Java você precisa criar um Objeto do Tipo
		 * Tema.
		 */

		/** Cria um "Formatador" para o objeto Date */ 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 

		/** Cria um objeto do tipo Date para enviar na requisição */ 
        Date data1 = formato.parse("2021-06-09");

		/** Cria e Instancia um objeto do tipo Postagem com o Tema nulo */ 
        postagem = new Postagem(0, "titulo", "texto da postagem", data1, null);
		
		/** Cria e Instancia um objeto do tipo Tema */ 
		Tema tema = new Tema(0, "Tema 01");

		/** Salva o objeto do tipo Tema */ 
		temaRepository.save(tema);

		/** Insere o objeto do tipo Tema no Objeto Postagem */ 
		postagem.setTema(tema);

	
		/** Criando o objeto para o Update 
		 * 
		 * A Postagem com id 26 somente será alterada se ela existir
		 * Verifique no Banco de Dados o Id correto
		 * 
		*/
		SimpleDateFormat formatoUpdate = new SimpleDateFormat("yyyy-MM-dd"); 
        Date dataUpdate = formatoUpdate.parse("2021-06-10");
        postagemUpdate = new Postagem(26L, "Título atualizado", "Texto da postagem Atualizado", dataUpdate, null);
		Tema temaUpdate = new Tema(0, "Tema 05");
		temaRepository.save(temaUpdate);
		postagemUpdate.setTema(temaUpdate);
		
	}

	@Test
    @DisplayName("✔ Cadastrar Postagem!")
	public void deveRealizarPostPostagem() {

		
		/*
		 * Cria um objeto do tipo HttpEntity para enviar como terceiro
		 * parâmentro do método exchange. (Enviando um objeto Postagem via body)
		 * 
		 * */
		HttpEntity<Postagem> request = new HttpEntity<Postagem>(postagem);

		ResponseEntity<Postagem> resposta = testRestTemplate.withBasicAuth("admin", "admin123").exchange("/postagens", HttpMethod.POST, request, Postagem.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

	}
	

	@Test
    @DisplayName("👍 Listar todas as Postagems!")
	public void deveMostrarTodosPostagem() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin", "admin123").exchange("/postagens", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
    @DisplayName("😳 Alterar Postagem!")
	public void deveRealizarPutPostagem() throws ParseException {

		HttpEntity<Postagem> request = new HttpEntity<Postagem>(postagemUpdate);

		ResponseEntity<Postagem> resposta = testRestTemplate.withBasicAuth("admin", "admin123").exchange("/postagens", HttpMethod.PUT, request, Postagem.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
	}

	@Test
	@DisplayName("😱 Deletar Postagem!")
	public void deveRealizarDeletePostagem() {

		/*
		 * A Postagem com Id 5 será apagado somente ele existir no Banco.
		 * Caso contrário, o teste irá falhar!
		 * Verifique no Banco de Dados o Id correto
		 * */
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin", "admin123").exchange("/postagens/5", HttpMethod.DELETE, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
	}

}
