package br.com.globalmotors.cars_service.entities.dtos;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import br.com.globalmotors.cars_service.entities.enums.CarsEnums.Countries;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BrandDTO {
	
	@Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "country_origin", nullable = false, columnDefinition = "countries")
    private Countries countryOrigin;

    @Column(name = "logo")
    private String logo;

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
