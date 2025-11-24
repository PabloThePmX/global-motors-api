package br.com.globalmotors.currencies_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class CurrenciesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenciesServiceApplication.class, args);
	}

}
