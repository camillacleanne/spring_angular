package br.org.generation.lojagames.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.generation.lojagames.model.Categoria;
import br.org.generation.lojagames.repository.CategoriaRepository;
import br.org.generation.lojagames.repository.ProdutoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Categoria> trendProducts(){
		
		List<Categoria> categorias = categoriaRepository.findAll();
		
		for (Categoria categoria : categorias) {
	
			categoria.setNumeroProdutos(produtoRepository.contarProdutos(categoria.getId()));
		}
		
		Collections.sort(categorias, Collections.reverseOrder(Comparator.comparing(Categoria::getNumeroProdutos)));
		return categorias;
	}
}

