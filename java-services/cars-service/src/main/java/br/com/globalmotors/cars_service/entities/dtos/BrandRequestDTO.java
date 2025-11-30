package br.com.globalmotors.cars_service.entities.dtos;

import br.com.globalmotors.cars_service.entities.enums.CarEnums.Countries;

public class BrandRequestDTO {
	
    private String name;

    private String description;
    
    private Countries countryOrigin;

    private String logo;
    
    //Getters and Setters

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
