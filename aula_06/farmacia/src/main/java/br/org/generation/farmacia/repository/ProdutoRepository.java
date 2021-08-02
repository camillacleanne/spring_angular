package br.org.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	 
	public List <Produto> findAllByNomeContainingIgnoreCase(String nome);
	// Equivalente a select * from tb_produtos where nome like "%xxxx%";
	
	public List <Produto> findByNomeOrLaboratorio(String nome, String laboratorio);
	// Equivalente a select * from tb_produtos where nome = xxx or laboratorio = xxx;
	
	public List <Produto> findByPrecoGreaterThan(BigDecimal preco);
	// Equivalente a select * from tb_produtos where preco > 000;

}
