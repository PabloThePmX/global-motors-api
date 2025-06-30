package br.com.globalmotors.cars_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CarsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsServiceApplication.class, args);
	}

}
