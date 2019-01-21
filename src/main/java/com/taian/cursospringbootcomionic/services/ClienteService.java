package com.taian.cursospringbootcomionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taian.cursospringbootcomionic.domain.Cidade;
import com.taian.cursospringbootcomionic.domain.Cliente;
import com.taian.cursospringbootcomionic.domain.Endereco;
import com.taian.cursospringbootcomionic.domain.enums.TipoCliente;
import com.taian.cursospringbootcomionic.dto.ClienteDTO;
import com.taian.cursospringbootcomionic.dto.ClienteNewDTO;
import com.taian.cursospringbootcomionic.repositories.ClienteRepository;
import com.taian.cursospringbootcomionic.repositories.EnderecoRepository;
import com.taian.cursospringbootcomionic.services.exception.DataIntegrityException;
import com.taian.cursospringbootcomionic.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Cliente> findAll() {
		List<Cliente> clientes = repo.findAll();
		return clientes;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id = " + id));

	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {

		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()), bCryptPasswordEncoder.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cid, cli);
		cli.getEnderecos().add(end);
		cli.getNumero().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getNumero().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getNumero().add(objDTO.getTelefone3());
		}

		return cli;

	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
