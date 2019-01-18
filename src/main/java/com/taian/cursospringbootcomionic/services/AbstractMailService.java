package com.taian.cursospringbootcomionic.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.taian.cursospringbootcomionic.domain.Pedido;

public abstract class AbstractMailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(simpleMailMessage);
		
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(obj.getCliente().getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Pedido nº" + obj.getId() + " confirmado com sucesso!");
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(obj.toString());
		return simpleMailMessage;
	}
}
