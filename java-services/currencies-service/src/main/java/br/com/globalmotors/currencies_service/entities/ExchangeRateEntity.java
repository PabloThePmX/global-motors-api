package br.com.globalmotors.currencies_service.entities;

import java.time.LocalDateTime;

import br.com.globalmotors.currencies_service.models.Currency.Currencies;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@IdClass(ExchangeRateId.class)
@Table(name = "exchange_rate")
public class ExchangeRateEntity {
	
	@Id
	@Column(name= "currency_source")
	private Currencies currencySource;
	
	@Id
	@Column(name= "currency_target")
	private Currencies currencyTarget;
	
	@Column(name= "rate")
	private double rate ;
	
	@Column(name= "last_updated")
	private LocalDateTime lastUpdated;
	
	@Transient
	private double convertedValue;
	
	@Transient
	private String dataSource;
	
	//Getters and Setters
	 
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdate) {
		this.lastUpdated = lastUpdate;
	}

	public double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(double convertedValue) {
		this.convertedValue = convertedValue;
	}
}
