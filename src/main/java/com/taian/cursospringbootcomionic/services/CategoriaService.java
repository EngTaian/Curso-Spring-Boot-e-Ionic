package com.taian.cursospringbootcomionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taian.cursospringbootcomionic.domain.Categoria;
import com.taian.cursospringbootcomionic.repositories.CategoriaRepository;
import com.taian.cursospringbootcomionic.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public List<Categoria> buscarAll() {
		List<Categoria> obj = repo.findAll();
		return obj;
	}

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}

}
