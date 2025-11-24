package br.com.globalmotors.currencies_service.clients;

import java.util.List;

import br.com.globalmotors.currencies_service.models.Currency;

public class CurrencyBCResponse {
	private List<Currency> value;
	
	//Getters and Setters
	
	public List<Currency> getValue() {
		return value;
	}

	public void setValue(List<Currency> value) {
		this.value = value;
	}
		
}