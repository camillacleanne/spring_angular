package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Postagem;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostagemRepositoryTest {
  
	/** Injeta o Repositório - PostagemRepository */
    @Autowired
	private PostagemRepository postagemRepository;
	
	@BeforeAll
	void start() throws ParseException {
		
		/** Instancia 4 objetos do tipo Date */

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date data1 = formato.parse("2021-06-07");
        Date data2 = formato.parse("2021-06-08");
		Date data3 = formato.parse("2021-06-09");
		Date data4 = formato.parse("2021-06-10");

		/** Instancia 4 objetos do tipo Postagem (sem Tema) e grava no Banco de Dados
		 *  se o titulo for diferente de null
		 */

        Postagem postagem = new Postagem(0, "Postagem 01", "Texto teste", data1);
		if(postagemRepository.findFirstByTituloIgnoreCase(postagem.getTitulo()) != null)
			postagemRepository.save(postagem);
		
		postagem = new Postagem(0, "Postagem 02", "Texto teste", data2);
		if(postagemRepository.findFirstByTituloIgnoreCase(postagem.getTitulo()) != null)
			postagemRepository.save(postagem);
		
		postagem = new Postagem(0, "Postagem 03", "Texto teste", data3);
		if(postagemRepository.findFirstByTituloIgnoreCase(postagem.getTitulo()) != null)
			postagemRepository.save(postagem);

		postagem = new Postagem(0, "Diferente 04", "Texto teste", data4);
		if(postagemRepository.findFirstByTituloIgnoreCase(postagem.getTitulo()) != null)
			postagemRepository.save(postagem);
	
        }
	
	@Test
	@DisplayName("💾 Retorna o Título")
	public void findByTituloRetornaTitulo() throws Exception {

		Postagem postagem = postagemRepository.findFirstByTituloIgnoreCase("Postagem 01").get();
		assertTrue(postagem.getTitulo().equals("Postagem 01"));
	}
	    
	@Test
	@DisplayName("💾 Retorna 3 postagens com o título semelhante")
	public void findAllBypostagemContainingIgnoreCaseRetornaTresPostagens() {

		/** Caso a tabela esteja com muitos dados cadastrados, este teste poderá falhar */

		List<Postagem> listaDepostagems = postagemRepository.findAllByTituloContainingIgnoreCase("postagem");
		assertEquals(3, listaDepostagems.size());
	}
	
	@AfterAll
	public void end() {
		
		/** Apaga todos os dados */

		postagemRepository.deleteAll();
	}
    
}
