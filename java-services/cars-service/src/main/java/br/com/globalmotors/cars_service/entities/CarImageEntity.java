package br.com.globalmotors.cars_service.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@IdClass(CarImageId.class)
@Table(name = "car_images")
public class CarImageEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "car_images_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Id
	@Column(name = "car", updatable = false, nullable = false)
	private UUID car;
	
	@Column(name = "image")
	private String image;
	
	//Getters and Setters
	
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
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
