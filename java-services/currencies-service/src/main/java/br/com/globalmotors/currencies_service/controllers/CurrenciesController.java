package br.com.globalmotors.currencies_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.currencies_service.clients.CurrencyBCClient;
import br.com.globalmotors.currencies_service.entities.ConversionRateEntity;
import br.com.globalmotors.currencies_service.repository.CurrencyRepo;

@RestController
@RequestMapping("currencies")
public class CurrenciesController {
	private final CurrencyRepo repo;
	private final CurrencyBCClient currencyBCClient;

	public CurrenciesController(CurrencyRepo repo, CurrencyBCClient currencyBCClient) {
		super();
		this.repo = repo;
		this.currencyBCClient = currencyBCClient;
	}
	
	@Value("${server.port}")
	private int serverPort;
	
	@GetMapping("/{value}/{current_currency}/{target_currency}")
	public ResponseEntity<ConversionRateEntity> getCurrencies(
			@PathVariable double value,
			@PathVariable String current_currency,
			@PathVariable String target_currency
			) throws Exception{
		ConversionRateEntity ConversionRate = repo.findBySourceAndTarget(current_currency, target_currency)
				.orElseThrow(() -> new Exception("Conversion Rate Unsupported"));
		
		current_currency = current_currency.toUpperCase();
		target_currency = target_currency.toUpperCase();
		String data_source = "None";
		
		//TODO (23:30, aula 10)
		//ConversionRateEntity conversionRate = new ConversionRateEntity();
		
		
		ConversionRate.setConvertedValue(value * ConversionRate.getConvertedValue());
		
		ConversionRate.setEnviroment("Currencies-service running on port: " + serverPort);
		
		return ResponseEntity.ok(ConversionRate);
	}
	
	
}
