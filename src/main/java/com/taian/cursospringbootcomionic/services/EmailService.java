package com.taian.cursospringbootcomionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.taian.cursospringbootcomionic.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
}