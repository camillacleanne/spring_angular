package br.org.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
	/**
	 * 
	 * A annotation @Query permite executar um consulta SQL nativa 
	 * (igual as consultas criadas no MySQL), dentro do Spring, onde:
	 * 
	 * Value -> A consulta propriamente dita. Observe que o parâmetro deve 
	 * ser precedido por : (:id), para indicar que é uma parâmetro passado
	 * via método
	 * 
	 * nativeQuery = true -> Indica que a consulta está no padrão SQL
	 * 
	 * O Método countPosts2 executará consulta e retornará um numero
	 * inteiro (total de Posts do tema_id informado), onde:
	 * 
	 * @Param("id") -> mapeia o parâmetro da consulta sql (:id)
	 * 
	 * long id -> é o parâmetro que será passado no método
	 * 
	 */

	@Query(value = "select count(tema_id) from tb_postagens where tema_id = :id", nativeQuery = true)
	public int countPosts(@Param("id") long id);

}
