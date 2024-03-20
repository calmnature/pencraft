package com.example.pencraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PencraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(PencraftApplication.class, args);
	}

}
