package com.taian.cursospringbootcomionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.taian.cursospringbootcomionic.domain.Pedido;
import com.taian.cursospringbootcomionic.repositories.PedidoRepository;
import com.taian.cursospringbootcomionic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public List<Pedido> findAll() {
		List<Pedido> obj = repo.findAll();
		return obj;
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado"));
	}

}
