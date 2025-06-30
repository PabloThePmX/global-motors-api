package br.com.globalmotors.cars_service.entities.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import br.com.globalmotors.cars_service.entities.enums.CarsEnums.CarCategories;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.CarConfigs;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.CarTypes;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.Colors;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.Currencies;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.PropulsionTypes;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.ShiftTypes;
import br.com.globalmotors.cars_service.entities.enums.CarsEnums.TractionTypes;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class CarDTO {
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "currency", nullable = false, columnDefinition = "currencies")
    private Currencies currency;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;
    
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "car_type", nullable = false, columnDefinition = "car_types")
    private CarTypes carType;

    @Column(name = "mileage")
    private BigDecimal mileage;

    @Column(name = "brand", nullable = false)
    private UUID brand;

    @Column(name = "store", nullable = false)
    private UUID store;

    @Column(name = "worldwide_quantity")
    private Integer worldwideQuantity;

    @Column(name = "national_quantity")
    private Integer nationalQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Colors color;

    @Column(name = "horse_power", nullable = false)
    private int horsePower;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "category", nullable = false, columnDefinition = "car_categories")
    private CarCategories category;

    @Column(name = "doors", nullable = false)
    private int doors;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "traction", nullable = false, columnDefinition = "traction_types")
    private TractionTypes traction;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "car_configuration", nullable = false, columnDefinition = "car_configs")
    private CarConfigs carConfiguration;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "shift", nullable = false, columnDefinition = "shift_types")
    private ShiftTypes shift;

    @Column(name = "acceleration_to_hundred")
    private BigDecimal accelerationToHundred;

    @Column(name = "torque")
    private String torque;

    @Column(name = "motorization")
    private String motorization;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "propulsion", nullable = false, columnDefinition = "propulsion_types")
    private PropulsionTypes propulsion;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "default_picture")
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

	public void setWorldwideQuantity(Integer worldwideQuantity) {
		this.worldwideQuantity = worldwideQuantity;
	}

	public Integer getNationalQuantity() {
		return nationalQuantity;
	}

	public void setNationalQuantity(Integer nationalQuantity) {
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
