package br.com.globalmotors.cars_service.entities.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.globalmotors.cars_service.entities.enums.CarEnums.CarCategories;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.CarConfigs;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.CarTypes;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.Colors;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.Currencies;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.PropulsionTypes;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.ShiftTypes;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.TractionTypes;

public class CarRequestDTO {

    private BigDecimal price;

    private Currencies currency;

    private String model;

    private int year;
    
    private CarTypes carType;

    private BigDecimal mileage;

    private UUID brand;

    private UUID store;

    private int worldwideQuantity;

    private int nationalQuantity;

    private Colors color;

    private int horsePower;

    private CarCategories category;

    private int doors;

    private TractionTypes traction;

    private CarConfigs carConfiguration;

    private ShiftTypes shift;

    private BigDecimal accelerationToHundred;

    private String torque;

    private String motorization;

    private PropulsionTypes propulsion;

    private Boolean isAvailable;

    private String defaultPicture;

    //Getters and Setters
    
    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currencies getCurrency() {
		return currency;
	}

	public void setCurrency(Currencies currency) {
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

	public CarTypes getCarType() {
		return carType;
	}

	public void setCarType(CarTypes carType) {
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

	public void setWorldwideQuantity(int worldwideQuantity) {
		this.worldwideQuantity = worldwideQuantity;
	}

	public Integer getNationalQuantity() {
		return nationalQuantity;
	}

	public void setNationalQuantity(int nationalQuantity) {
		this.nationalQuantity = nationalQuantity;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public CarCategories getCategory() {
		return category;
	}

	public void setCategory(CarCategories category) {
		this.category = category;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public TractionTypes getTraction() {
		return traction;
	}

	public void setTraction(TractionTypes traction) {
		this.traction = traction;
	}

	public CarConfigs getCarConfiguration() {
		return carConfiguration;
	}

	public void setCarConfiguration(CarConfigs carConfiguration) {
		this.carConfiguration = carConfiguration;
	}

	public ShiftTypes getShift() {
		return shift;
	}

	public void setShift(ShiftTypes shift) {
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

	public PropulsionTypes getPropulsion() {
		return propulsion;
	}

	public void setPropulsion(PropulsionTypes propulsion) {
		this.propulsion = propulsion;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getDefaultPicture() {
		return defaultPicture;
	}

	public void setDefaultPicture(String defaultPicture) {
		this.defaultPicture = defaultPicture;
	}   
}
