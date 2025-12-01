package br.com.globalmotors.currencies_service.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.currencies_service.clients.CurrencyBCClient;
import br.com.globalmotors.currencies_service.clients.CurrencyBCResponse;
import br.com.globalmotors.currencies_service.entities.ExchangeRateEntity;
import br.com.globalmotors.currencies_service.models.Currency.Currencies;
import br.com.globalmotors.currencies_service.repository.CurrencyRepo;

@RestController
@RequestMapping("currencies")
public class CurrencyController {
	
	private final CurrencyBCClient currencyBcClient;
	private final CurrencyRepo repository; 
	private final CacheManager cacheManager;
	
	private LocalDateTime conversionDate; 
	
	public CurrencyController(CurrencyRepo repository, CurrencyBCClient currencyBcClient, CacheManager cacheManager){
		this.repository = repository;
		this.currencyBcClient = currencyBcClient;
		this.cacheManager = cacheManager;
	}
	
	//vai buscar do banco central
	//vai salvar isso em cache e no banco
	//se o banco central estiver fora e sem cache, vai pegar do banco o ultimo valor
	
	@GetMapping("/{value}/{source}/{target}")
	public ResponseEntity<ExchangeRateEntity> getCurrency(@PathVariable double value,
			@PathVariable String source, @PathVariable String target) throws Exception {
		
		source = source.toUpperCase();
		target = target.toUpperCase();
		String dataSource = "None";
		String nameCache = "Currency";
		String keyCache = source + target;
		
		if(!EnumUtils.isValidEnum(Currencies.class, source) || !EnumUtils.isValidEnum(Currencies.class, target))
			throw new Exception("Moeda sem suporte.");
		
		ExchangeRateEntity conversionRate = cacheManager.getCache(nameCache).get(keyCache, ExchangeRateEntity.class);
		
		if(conversionRate != null) {
			conversionRate.setDataSource("Cache");
			conversionRate.setConvertedValue(value * conversionRate.getRate());
			return ResponseEntity.ok(conversionRate);
		}
		
		conversionRate = new ExchangeRateEntity();
		try {
			//divide pq precisa ver quanto o "target cabe no source"
			conversionRate.setRate(getConversionRateFromApi(source.toUpperCase())/getConversionRateFromApi(target.toUpperCase()));
			dataSource = "API BCB";
			conversionRate.setLastUpdated(conversionDate);
		} catch (Exception e) {
			conversionRate = repository.findByCurrencySourceAndCurrencyTarget(Currencies.valueOf(source), Currencies.valueOf(target))
					.orElseThrow(() -> new Exception("Moeda sem suporte."));
			dataSource = "Local Database";
		}
		
		conversionRate.setCurrencySource(Currencies.valueOf(source));
		conversionRate.setCurrencyTarget(Currencies.valueOf(target));
		conversionRate.setDataSource(dataSource);
		conversionRate.setConvertedValue(value * conversionRate.getRate());
		
		repository.save(conversionRate);
		cacheManager.getCache(nameCache).put(keyCache, conversionRate);

		return ResponseEntity.ok(conversionRate);
	}
	
	private double getConversionRateFromApi(String currency) throws Exception {
		
		//se for brl, não converte, mas envia 1 para pelo menos não precisar tratar nem condicionar na hora da divisão
		if(currency.equals("BRL"))
			return 1;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String dateString = dtf.format(LocalDate.now());
		CurrencyBCResponse response = currencyBcClient.getCurrency(currency, dateString);
		
		//caso não conseguir do dia, vai tentar pelo menos nos 3 dias anteriores antes de dar erro
		int daysAgo = 1;
		while(response.getValue().isEmpty() || response.getValue().size() <= 0) {
			dateString = dtf.format(LocalDate.now().minusDays(daysAgo));
			response = currencyBcClient.getCurrency(currency, dateString);
			
			if(daysAgo >= 4)
				throw new Exception("Não foi possível se comuinicar com a api do Banco Central.");
			
			daysAgo++;
		}
		
		//transforma em datetime
		LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		LocalTime now = LocalTime.now();
		var d = LocalDateTime.of(date, now);
		conversionDate = d;
		
		//pega o último valor pois é o mais recente
		return response.getValue().get(response.getValue().size() - 1).getCotacaoVenda(); 
	}
	
}
