package br.com.globalmotors.cars_service.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_images")
public class CarImageEntity {
	
	@Id
	@Column(name = "car")
	private String car;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "image")
	private String image;
	
	//Getters and Setters
	
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
