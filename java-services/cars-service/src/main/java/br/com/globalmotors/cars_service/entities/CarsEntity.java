package br.com.globalmotors.cars_service.entities;

import java.awt.Color;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_cars")
public class CarsEntity {
	    @Id
	    @GeneratedValue
	    private Long id;

	    @Column(nullable = false, precision = 12, scale = 2)
	    private BigDecimal price;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Currency currency;

	    @Column(length = 255, nullable = false)
	    private String model;

	    @Column(nullable = false)
	    private int year;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "car_type", nullable = false)
	    private String carType;

	    @Column(nullable = false, precision = 8, scale = 2)
	    private BigDecimal mileage;

	    @Column(nullable = false)
	    private UUID brand;

	    @Column(nullable = false)
	    private UUID store;

	    private Integer worldwideQuantity;
	    private Integer nationalQuantity;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Color color;

	    @Column(name = "horse_power", nullable = false)
	    private int horsePower;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private String category;

	    @Column(nullable = false)
	    private int doors;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private String traction;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "car_configuration", nullable = false)
	    private String carConfiguration;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private String  shift;

	    @Column(name = "acceleration_to_hundred", precision = 10, scale = 2)
	    private BigDecimal accelerationToHundred;

	    @Column(length = 100)
	    private String torque;

	    @Column(length = 100)
	    private String motorization;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private String propulsion;

	    @Column(name = "is_available")
	    private boolean isAvailable;

	    @Column(name = "default_picture", length = 255)
	    private String defaultPicture;

	    @Column(name = "last_update_datetime")
	    private LocalDateTime lastUpdateDatetime;
	    
	    @Transient
	    private String enviroment;
	    @Transient
	    private double convertedPrice;
	    
	    // Getters and Setters
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public Currency getCurrency() {
			return currency;
		}
		public void setCurrency(Currency currency) {
			this.currency = currency;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getCarType() {
			return carType;
		}
		public void setCarType(String carType) {
			this.carType = carType;
		}
		public BigDecimal getMileage() {
			return mileage;
		}
		public void setMileage(BigDecimal mileage) {
			this.mileage = mileage;
		}
		public UUID getBrand() {
			return brand;
		}
		public void setBrand(UUID brand) {
			this.brand = brand;
		}
		public UUID getStore() {
			return store;
		}
		public void setStore(UUID store) {
			this.store = store;
		}
		public Integer getWorldwideQuantity() {
			return worldwideQuantity;
		}
		public void setWorldwideQuantity(Integer worldwideQuantity) {
			this.worldwideQuantity = worldwideQuantity;
		}
		public Integer getNationalQuantity() {
			return nationalQuantity;
		}
		public void setNationalQuantity(Integer nationalQuantity) {
			this.nationalQuantity = nationalQuantity;
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
		public int getHorsePower() {
			return horsePower;
		}
		public void setHorsePower(int horsePower) {
			this.horsePower = horsePower;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public int getDoors() {
			return doors;
		}
		public void setDoors(int doors) {
			this.doors = doors;
		}
		public String getTraction() {
			return traction;
		}
		public void setTraction(String traction) {
			this.traction = traction;
		}
		public String getCarConfiguration() {
			return carConfiguration;
		}
		public void setCarConfiguration(String carConfiguration) {
			this.carConfiguration = carConfiguration;
		}
		public String getShift() {
			return shift;
		}
		public void setShift(String shift) {
			this.shift = shift;
		}
		public BigDecimal getAccelerationToHundred() {
			return accelerationToHundred;
		}
		public void setAccelerationToHundred(BigDecimal accelerationToHundred) {
			this.accelerationToHundred = accelerationToHundred;
		}
		public String getTorque() {
			return torque;
		}
		public void setTorque(String torque) {
			this.torque = torque;
		}
		public String getMotorization() {
			return motorization;
		}
		public void setMotorization(String motorization) {
			this.motorization = motorization;
		}
		public String getPropulsion() {
			return propulsion;
		}
		public void setPropulsion(String propulsion) {
			this.propulsion = propulsion;
		}
		public boolean isAvailable() {
			return isAvailable;
		}
		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}
		public String getDefaultPicture() {
			return defaultPicture;
		}
		public void setDefaultPicture(String defaultPicture) {
			this.defaultPicture = defaultPicture;
		}
		public LocalDateTime getLastUpdateDatetime() {
			return lastUpdateDatetime;
		}
		public void setLastUpdateDatetime(LocalDateTime lastUpdateDatetime) {
			this.lastUpdateDatetime = lastUpdateDatetime;
		}
		public String getEnviroment() {
			return enviroment;
		}
		public void setEnviroment(String enviroment) {
			this.enviroment = enviroment;
		}
		public double getConvertedPrice() {
			return convertedPrice;
		}
		public void setConvertedPrice(double convertedPrice) {
			this.convertedPrice = convertedPrice;
		}
	    
	    
}	