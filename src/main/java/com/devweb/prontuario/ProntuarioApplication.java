package com.devweb.prontuario;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.devweb.prontuario.config.RsaKeyProperties;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ProntuarioApplication{


	@Bean
	public ModelMapper mapper(){

		return new ModelMapper();

	}


	public static void main(String[] args) {
		SpringApplication.run(ProntuarioApplication.class, args);
	}



}
