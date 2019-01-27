package com.taian.cursospringbootcomionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.taian.cursospringbootcomionic.services.DbService;
import com.taian.cursospringbootcomionic.services.EmailService;
import com.taian.cursospringbootcomionic.services.MockEmailService;

@Configuration
@Profile("test")
public class DbTestConfiguration {
	
	@Autowired
	private DbService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String db;
	
	@Bean
	public boolean dbTest() throws ParseException {
		
		if(!"create".equals(db)) {
			return false;
		}
		dbService.instantiateDbServerTest();
		return true;
		
	} 
	
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
