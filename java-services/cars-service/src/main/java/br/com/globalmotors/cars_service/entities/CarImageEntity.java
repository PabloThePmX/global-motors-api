package br.com.globalmotors.cars_service.entities;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.globalmotors.cars_service.entities.dtos.CarImageDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_images")
public class CarImageEntity extends CarImageDTO{
	
	@Id
	@UuidGenerator
	@GeneratedValue
	private UUID id;
	
	//Getters and Setters
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
}
