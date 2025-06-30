package br.com.globalmotors.cars_service.entities;

import java.time.LocalDateTime;

@Entity
@Table (name = "stores")
public class StoreEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column (name = "name")
	private String name;
	@Column (name = "address")
	private String address;
	private String cnpj;
	@Column (name = "cnpj")
	private String email;
	@Column (name = "phone_number")
	private String phone_number;
	@Column (name = "website")
	private String website;
	@Column (name = "description")
	private String descprition;
	@Column (name = "owner")
	private String owner;
	@Column (name = "is_active")
	private boolean is_active;
	@Column (name = "last_update_datetime")
	private LocalDateTime last_update;
	
	//Getters and Setters
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
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
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescprition() {
		return descprition;
	}
	public void setDescprition(String descprition) {
		this.descprition = descprition;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public LocalDateTime getLast_update() {
		return last_update;
	}
	public void setLast_update(LocalDateTime last_update) {
		this.last_update = last_update;
	}
	
	
}
