package br.com.globalmotors.currencies_service.models;

public class Currency {
	
	public static enum currencies {
		USD,
		EUR,
		BRL
	}
	
	private double cotacaoVenda;
	
	//Getters and Setters
	
	public double getCotacaoVenda() {
		return cotacaoVenda;
	}

	public void setCotacaoVenda(double cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}
}
