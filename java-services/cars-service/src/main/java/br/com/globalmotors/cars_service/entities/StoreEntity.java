package br.com.globalmotors.cars_service.entities;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.globalmotors.cars_service.entities.dtos.StoreDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stores")
public class StoreEntity extends StoreDTO {
	
	@Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

	//Getters and Setters
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
}
