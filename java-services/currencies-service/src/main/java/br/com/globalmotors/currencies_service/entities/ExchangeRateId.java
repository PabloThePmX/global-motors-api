package br.com.globalmotors.currencies_service.entities;

import java.io.Serializable;

import br.com.globalmotors.currencies_service.models.Currency.Currencies;

public class ExchangeRateId implements Serializable {
	
	public ExchangeRateId() {}
	
	private Currencies currencySource;
	
	private Currencies currencyTarget;

	public Currencies getCurrencySource() {
		return currencySource;
	}

	public void setCurrencySource(Currencies currencySource) {
		this.currencySource = currencySource;
	}

	public Currencies getCurrencyTarget() {
		return currencyTarget;
	}

	public void setCurrencyTarget(Currencies currencyTarget) {
		this.currencyTarget = currencyTarget;
	}
	
}
