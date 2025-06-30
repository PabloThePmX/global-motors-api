package br.com.globalmotors.cars_service.entities;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class BrandEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name")
	private String name;
	@Column (name = "description")
	private String description;
	@Column (name = "country_origin")
	private String country_origin;
	@Column (name = "logo")
	private String logo;
	@Column (name = "last_update_datetime")
	private LocalDateTime last_update;
	
	// Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getCountry_origin() {
		return country_origin;
	}
	public void setCountry_origin(String country_origin) {
		this.country_origin = country_origin;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public LocalDateTime getLast_update() {
		return last_update;
	}
	public void setLast_update(LocalDateTime last_update) {
		this.last_update = last_update;
	}
	
	
	
}
