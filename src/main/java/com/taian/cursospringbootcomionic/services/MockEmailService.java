package com.taian.cursospringbootcomionic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractMailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Simulando envio de e-mail....");
		LOGGER.info(msg.toString());
		LOGGER.info("E-mail enviado");
	}
	
}
