package com.taian.cursospringbootcomionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.taian.cursospringbootcomionic.services.DbService;

@Configuration
@Profile("test")
public class DbTestConfiguration {
	
	@Autowired
	DbService dbService;
	
	@Bean
	public boolean dbTest() throws ParseException {
		
		dbService.instantiateDbServerTest();
		return true;
		
	} 
	
}
