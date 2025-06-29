package br.com.globalmotors.currencies_service.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "conversion_rate")
public class ConversionRateEntity {
	
	@Column(name= "currency")
	private String currencies;
	
	@Column(name= "rate")
	private double rate ;
	
	@Column(name= "last_updated")
	private LocalDateTime lastUpdate;
	
	@Transient
	private double convertedValue;
	
	@Transient
	private String enviroment;
	
	//Getters and Setters
	 
	public String getCurrencies() {
		return currencies;
	}

	public void setCurrencies(String currencies) {
		this.currencies = currencies;
	}


	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}
	
	
}
