package com.taian.cursospringbootcomionic.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taian.cursospringbootcomionic.domain.Cliente;
import com.taian.cursospringbootcomionic.domain.ItemPedido;
import com.taian.cursospringbootcomionic.domain.PagamentoComBoleto;
import com.taian.cursospringbootcomionic.domain.Pedido;
import com.taian.cursospringbootcomionic.domain.Produto;
import com.taian.cursospringbootcomionic.domain.enums.EstadoPagamento;
import com.taian.cursospringbootcomionic.repositories.ClienteRepository;
import com.taian.cursospringbootcomionic.repositories.ItemPedidoRepository;
import com.taian.cursospringbootcomionic.repositories.PagamentoRepository;
import com.taian.cursospringbootcomionic.repositories.PedidoRepository;
import com.taian.cursospringbootcomionic.repositories.ProdutoRepository;
import com.taian.cursospringbootcomionic.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Pedido> findAll() {
		List<Pedido> obj = repo.findAll();
		return obj;
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado"));
	}
	
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);   
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
