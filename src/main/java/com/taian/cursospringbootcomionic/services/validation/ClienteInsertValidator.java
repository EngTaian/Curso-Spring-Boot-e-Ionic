package com.taian.cursospringbootcomionic.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.taian.cursospringbootcomionic.domain.Cliente;
import com.taian.cursospringbootcomionic.domain.enums.TipoCliente;
import com.taian.cursospringbootcomionic.dto.ClienteNewDTO;
import com.taian.cursospringbootcomionic.repositories.ClienteRepository;
import com.taian.cursospringbootcomionic.resources.exception.FieldMessage;
import com.taian.cursospringbootcomionic.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && ! BR.isValidSsn(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF Inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && ! BR.isValidTin(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "Cnpj Inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "E-mail já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
