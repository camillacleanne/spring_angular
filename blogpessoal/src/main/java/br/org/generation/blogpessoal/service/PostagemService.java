package br.org.generation.blogpessoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@Service
public class PostagemService {

	/* Injeção de dependência (PostagemRepository) */
	@Autowired
	private PostagemRepository postagemRepository;
		
	public Postagem curtir(Long id) {
		
		/** Busca a Postagem no Banco de Dados pelo Id utilizabdo o método 
		 *  buscarPostagemPeloId(id) implmentado logo abaixo
		*/

		Postagem postagem = buscarPostagemPeloId(id);
		
		/**
		 * Se o Método retornou a Postagem, soma 1 curtida
		 * no atributo curtidas através do método 
		 * Set que altera o atributo e do método Get que
		 * recupera o atributo do Banco de Dados
		 */

		postagem.setCurtidas(postagem.getCurtidas() + 1);
		
		/**
		 *  Retorna para a Classe Controller a postagem
		 * atualizada com uma nova curtida
		 */

		return postagemRepository.save(postagem);
	
	}
	
public Postagem descurtir(Long id) {
		
		/** Busca a Postagem no Banco de Dados pelo Id 
		 *  utilizabdo o método buscarPostagemPeloId(id)
		 *  implmentado logo abaixo
		*/

		Postagem postagem = buscarPostagemPeloId(id);
		
		/**
		 * Verifica se o numero de curtidas da postagem é maior
		 * do que zero (Não existe curtida negativa)
		 * 
		 * Se o Método retornou a Postagem e o numero de curtidas
		 * é maior do que zero, subtrai 1 curtida
		 * no atributo curtidas através do método 
		 * Set que altera o atributo e do método Get que
		 * recupera o atributo do Banco de Dados.
		 * 
		 * Caso contrário, define como 0 curtidas
		 * 
		 */


		if (postagem.getCurtidas() > 0) {
			
			postagem.setCurtidas(postagem.getCurtidas() - 1);
			
		}else {
			
			postagem.setCurtidas(0);
		
		}
		
		/**
		 *  Retorna para a Classe Controller a postagem
		 * atualizada com menos 1 curtida (Deslike)
		 */

		return postagemRepository.save(postagem);
	
	}
	
	/**
	 * Método de Busca implementado na Classe de serviço,
	 * que além de simplificar o processo elimina a necessidade
	 * de usar o Optional
	 */
	private Postagem buscarPostagemPeloId(Long id) {
		
		/**
		 * Busca uma Postagem pelo Id e caso não encontre retorna null
		 */

        Postagem postagemSalva = postagemRepository.findById(id).orElse(null);

		/**
		 *  Verifica o retorni do método
		 * 
		 *  Se for null, retorna uma Exception
		 * 
		 *  Caso contrário, retorna a postagem
		 */

        if (postagemSalva == null) {
             
        	throw new EmptyResultDataAccessException(1);
        } 
        
        	return postagemSalva;
    }
	
	
}
