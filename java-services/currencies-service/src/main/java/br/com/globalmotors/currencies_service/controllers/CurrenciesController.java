package br.com.globalmotors.currencies_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.currencies_service.clients.CurrencyBCClient;
import br.com.globalmotors.currencies_service.clients.CurrencyBCResponse;
import br.com.globalmotors.currencies_service.entities.ConversionRateEntity;
import br.com.globalmotors.currencies_service.repository.CurrencyRepo;

@RestController
@RequestMapping("currencies")
public class CurrenciesController {
	private final CurrencyRepo repo;
	private final CurrencyBCClient currencyBCClient;
	private final CacheManager cache;

	public CurrenciesController(CurrencyRepo repo, CurrencyBCClient currencyBCClient, CacheManager cache) {
		super();
		this.repo = repo;
		this.currencyBCClient = currencyBCClient;
		this.cache = cache;
	}

	@Value("${server.port}")
	private int serverPort;

	@GetMapping("/{value}/{current_currency}/{target_currency}")
	public ResponseEntity<ConversionRateEntity> getCurrencies(@PathVariable double value,
			@PathVariable String current_currency, @PathVariable String target_currency) throws Exception {

		current_currency = current_currency.toUpperCase();
		target_currency = target_currency.toUpperCase();
		String data_source = "None";
		String nameCache = "Currency";
		String keyCache = current_currency + target_currency;

		ConversionRateEntity conversionRate = cache.getCache(nameCache).get(keyCache, ConversionRateEntity.class);

		if (conversionRate != null) {
			data_source = "Cache";
		} else {
			conversionRate = new ConversionRateEntity();
			conversionRate.setCurrencies(current_currency);
			conversionRate.setCurrencies(target_currency);
			if (current_currency.equals(target_currency)) {
				conversionRate.setConvertedValue(1);
			} else {
				try {
					double Source = 1;
					double Target = 1;
					if (!current_currency.equals("BRL")) {
						CurrencyBCResponse answer = currencyBCClient.getCurrency(current_currency, data_source);
						if (answer.getValue().isEmpty())
							throw new Exception("Currency not found for " + current_currency);
						Source = answer.getValue().get(0).getCotacaoVenda();
					}
					if (!target_currency.equals("BRL")) {
						CurrencyBCResponse answer = currencyBCClient.getCurrency(target_currency, data_source);
						if (answer.getValue().isEmpty())
							throw new Exception("Currency not found for " + target_currency);
						Target = answer.getValue().get(0).getCotacaoVenda();
					}
					conversionRate.setConvertedValue(Source / Target);
					data_source = "API BCB";
				} catch (Exception e) {
					conversionRate = repo.findBySourceAndTarget(current_currency, target_currency)
							.orElseThrow(() -> new Exception("Currency Unsupported"));
					data_source = "Local Database";
				}
			}
			cache.getCache(nameCache).put(keyCache, conversionRate);
		}

		conversionRate.setConvertedValue(value * conversionRate.getConvertedValue());
		conversionRate.setEnviroment("Currencies-service running on port: " + serverPort);

		return ResponseEntity.ok(conversionRate);

	}

}
