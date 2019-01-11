package com.taian.cursospringbootcomionic.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taian.cursospringbootcomionic.domain.Pedido;
import com.taian.cursospringbootcomionic.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private  PedidoService service;
	
	@RequestMapping(method= RequestMethod.GET)
	public List<Pedido> listaPedidos(){
		List<Pedido> obj = service.findAll();
		return obj;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Pedido findById(Integer id) {
		Pedido obj = service.findById(id);
		return obj;
	}
	
	
}
