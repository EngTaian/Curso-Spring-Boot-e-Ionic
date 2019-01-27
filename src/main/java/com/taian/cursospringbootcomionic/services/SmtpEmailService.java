package com.taian.cursospringbootcomionic.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class SmtpEmailService extends AbstractMailService {

	
	@Autowired
	private MailSender mailSender;
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Envio de e-mail");
		mailSender.send(msg);
		LOGGER.info("E-mail enviado com sucesso");
	}
	
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOGGER.info("Enviando E-mail....");
		javaMailSender.send(msg);
		LOGGER.info("E-mail enviado");
	}
	
}
