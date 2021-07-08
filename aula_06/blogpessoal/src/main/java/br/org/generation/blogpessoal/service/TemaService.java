package br.org.generation.blogpessoal.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.PostagemRepository;
import br.org.generation.blogpessoal.repository.TemaRepository;

@Service
public class TemaService {

	/** Injeção de dependência das Classes PostagemRepository 
	 *  e TemaRepository.
	 *  
	 *  Neste código iremos trabalhar com as 2 classes Repositório
	*/
	@Autowired
	private TemaRepository temaRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	public List<Tema> trendTopics(){
		
		/**
		 * Através do método findAll(), vamos criar uma List do tipo Tema
		 * Contendo todos os Temas cadastrados na tabela de temas
		 */
		List<Tema> temas = temaRepository.findAll();
		
		/**
		 * Através do looping for, iremos percorrer toda a List
		 * e executar o método countPosts(id) para contar todas as postagens
		 * de cada na tabela postagens e setar o atributo qtdTemas com a quantidade
		 */
		for (Tema tema : temas) {
	
			tema.setQtdTema(postagemRepository.countPosts(tema.getId()));
		}
		
		/**
		 *  Após finalizar o looping, iremos ordenar a List, onde:
		 * 
		 *  Collections.sort -> É um método da Classe Collections,
		 *  usado para classificar os elementos presentes na lista 
		 *  especificada da Coleção em ordem crescente. 
		 * 
		 *  Comparator.comparing -> É um método da classe Comparator, 
		 *  que retorna um objeto Comparator que usará o campo especificado 
		 *  como a chave de classificação. Neste caso o Objeto é da Classe Tema
		 *  e a chave de classificação é o atributo qtdTema. A classificação será
		 *  realizada em ordem crescente.
		 * 
		 *  Collections.reverseOrder -> Como queremos o resultado em ordem Descendente,
		 *  ou seja, do maior para o menor, utilizamos o método reverseOrder, da 
		 *  Classe Collections, para inverter a ordem de ordenação
		 *  
		 */

		Collections.sort(temas, Collections.reverseOrder(Comparator.comparing(Tema::getQtdTema)));
		
		/**
		 *  Retorna a List temas ordenada para a classe TemaController
		 */

		return temas;
	}
}
