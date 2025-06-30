package br.com.globalmotors.cars_service.entities.dtos;

import java.util.UUID;

import jakarta.persistence.Column;

public class StoreDTO {
	 @Column(name = "name", nullable = false)
	    private String name;

	    @Column(name = "address")
	    private UUID address;

	    @Column(name = "cnpj", nullable = false)
	    private String cnpj;

	    @Column(name = "email", nullable = false)
	    private String email;

	    @Column(name = "phone_number")
	    private String phoneNumber;

	    @Column(name = "website")
	    private String website;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "owner", nullable = false)
	    private UUID owner;

	    @Column(name = "is_active", nullable = false)
	    private boolean isActive;
	    
	    //Getters and Setters 
	    
	    public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public UUID getAddress() {
			return address;
		}
		public void setAddress(UUID address) {
			this.address = address;
		}
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public UUID getOwner() {
			return owner;
		}
		public void setOwner(UUID owner) {
			this.owner = owner;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public boolean isActive() {
			return isActive;
		}
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
}
