package br.com.globalmotors.currencies_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrenciesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenciesServiceApplication.class, args);
	}

}
