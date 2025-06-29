package br.com.globalmotors.currencies_service.clients;

import java.util.List;

public class CurrencyBCResponse {
	private List<CurrencyBC> value;
	
	//Getters and Setters
	
	public List<CurrencyBC> getValue() {
		return value;
	}

	public void setValue(List<CurrencyBC> value) {
		this.value = value;
	}
	
	
	public class CurrencyBC {
		private double cotacaoVenda;
		
		//Getters and Setters
		
		public double getCotacaoVenda() {
			return cotacaoVenda;
		}

		public void setCotacaoVenda(double cotacaoVenda) {
			this.cotacaoVenda = cotacaoVenda;
		}
		
		
	}

	
}
