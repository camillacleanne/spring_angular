package br.org.generation.blogpessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostagemTest {
    
    public Postagem postagem;
    public Postagem postagemErro = new Postagem();
	
	@Autowired
    private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() throws ParseException {
		
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date data = formato.parse("2021-06-10");
        Tema tema = new Tema(0, "Tema 01");
		postagem = new Postagem(0, "Postagem Teste", "Texto teste", data, tema);

	}

	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {
		Set<ConstraintViolation<Postagem>> violacao = validator.validate(postagem);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}
	
	@Test
	@DisplayName("❌ Valida Atributos Nulos")
	void testValidaAtributosNulos() {
		postagemErro.setTexto("Texto da postagem de erro");

		Set<ConstraintViolation<Postagem>> violacao = validator.validate(postagemErro);
		System.out.println(violacao.toString());
		assertFalse(violacao.isEmpty());
	}

}
