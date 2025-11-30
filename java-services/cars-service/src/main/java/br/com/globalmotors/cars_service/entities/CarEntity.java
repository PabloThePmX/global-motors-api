package br.com.globalmotors.cars_service.entities;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.globalmotors.cars_service.entities.enums.CarEnums.CarCategories;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.CarConfigs;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.CarTypes;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.Colors;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.Currencies;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.PropulsionTypes;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.ShiftTypes;
import br.com.globalmotors.cars_service.entities.enums.CarEnums.TractionTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class CarEntity {
	
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "currency", nullable = false)
    private Currencies currency;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;
    
    @Column(name = "car_type", nullable = false)
    private CarTypes carType;

    @Column(name = "mileage")
    private BigDecimal mileage;

    @Column(name = "brand", nullable = false)
    private UUID brand;

    @Column(name = "store", nullable = false)
    private UUID store;

    @Column(name = "worldwide_quantity")
    private int worldwideQuantity;

    @Column(name = "national_quantity")
    private int nationalQuantity;

    @Column(name = "color", nullable = false)
    private Colors color;

    @Column(name = "horse_power", nullable = false)
    private int horsePower;

    @Column(name = "category", nullable = false)
    private CarCategories category;

    @Column(name = "doors", nullable = false)
    private int doors;

    @Column(name = "traction", nullable = false)
    private TractionTypes traction;

    @Column(name = "car_configuration", nullable = false)
    private CarConfigs carConfiguration;

    @Column(name = "shift", nullable = false)
    private ShiftTypes shift;

    @Column(name = "acceleration_to_hundred")
    private BigDecimal accelerationToHundred;

    @Column(name = "torque")
    private String torque;

    @Column(name = "motorization")
    private String motorization;

    @Column(name = "propulsion", nullable = false)
    private PropulsionTypes propulsion;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "default_picture")
    private String defaultPicture;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public int getWorldwideQuantity() {
		return worldwideQuantity;
	}

	public void setWorldwideQuantity(int worldwideQuantity) {
		this.worldwideQuantity = worldwideQuantity;
	}

	public int getNationalQuantity() {
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