package com.rzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.rzk.model")
@SpringBootApplication
public class AnimalCaraServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalCaraServiceApplication.class, args);
	}
 
}
