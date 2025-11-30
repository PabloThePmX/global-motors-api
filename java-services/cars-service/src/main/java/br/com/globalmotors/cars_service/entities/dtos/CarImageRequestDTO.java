package br.com.globalmotors.cars_service.entities.dtos;

import java.util.UUID;

public class CarImageRequestDTO {
	
	private String image;
	
	private UUID car;
	
	//Getters and Setters
	
	public UUID getCar() {
		return car;
	}
	public void setCar(UUID car) {
		this.car = car;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
