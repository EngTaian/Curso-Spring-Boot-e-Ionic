package com.taian.cursospringbootcomionic.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import com.taian.cursospringbootcomionic.domain.Categoria;
import com.taian.cursospringbootcomionic.domain.Produto;
import com.taian.cursospringbootcomionic.repositories.CategoriaRepository;
import com.taian.cursospringbootcomionic.repositories.ProdutoRepository;
import com.taian.cursospringbootcomionic.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<Produto> search(String nome, List<Integer> id,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(id);
		return repo.search(nome, categorias, pageRequest);
	}
	
	public Page<Produto> searchSemQuery(String nome, List<Integer> id,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(id);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
	public List<Produto> findAll(){
		List<Produto> obj = repo.findAll();
		return obj;
	}
	
	public Produto findById(Integer id){
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto com Id = "+ id + " não foi encontrado"));
	}	
}
