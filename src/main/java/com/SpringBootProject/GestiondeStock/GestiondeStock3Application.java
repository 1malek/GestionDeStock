package com.SpringBootProject.GestiondeStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan
//on a des class audité il faut activé l'auditing
public class GestiondeStock3Application {

	public static void main(String[] args) {
		SpringApplication.run(GestiondeStock3Application.class, args);
	}

}
