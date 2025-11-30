package br.com.globalmotors.cars_service.entities;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.globalmotors.cars_service.entities.enums.CarEnums.Countries;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class BrandEntity {
	
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name = "country_origin", nullable = false)
    private Countries countryOrigin;

    @Column(name = "logo")
    private String logo;
    
    //Getters and Setters
    
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Countries getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(Countries countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
