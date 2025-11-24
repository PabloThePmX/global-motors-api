package br.com.globalmotors.currencies_service.entities;

import java.io.Serializable;

import br.com.globalmotors.currencies_service.models.Currency.currencies;

public class ExchangeRateId implements Serializable {
	
	private currencies currencySource;
	
	private currencies currencyTarget;
	
	public ExchangeRateId() {}
}
