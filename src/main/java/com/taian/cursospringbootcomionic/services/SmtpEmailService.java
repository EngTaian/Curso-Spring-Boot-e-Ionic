package com.taian.cursospringbootcomionic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public abstract class SmtpEmailService extends AbstractMailService {

	
	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Envio de e-mail");
		mailSender.send(msg);
		LOGGER.info("E-mail enviado com sucesso");
	}
	
}
