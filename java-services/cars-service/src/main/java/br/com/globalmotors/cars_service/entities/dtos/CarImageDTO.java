package br.com.globalmotors.cars_service.entities.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class CarImageDTO {
	@Column(name = "image")
	private String image;
	
	@Id
	@Column(name = "car")
	private String car;
	
	//Getters and Setters
	
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
