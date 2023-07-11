package com.prueba.credibanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.prueba.credibanco")
public class CredibancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CredibancoApplication.class, args);
	}

}
