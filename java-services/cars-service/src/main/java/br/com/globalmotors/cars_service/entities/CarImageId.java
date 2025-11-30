package br.com.globalmotors.cars_service.entities;

import java.io.Serializable;
import java.util.UUID;

public class CarImageId implements Serializable {
	
	public CarImageId() { }
	
	private int id;
	
	private UUID car;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UUID getCar() {
		return car;
	}

	public void setCar(UUID car) {
		this.car = car;
	}
}
