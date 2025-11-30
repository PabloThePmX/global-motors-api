package br.com.globalmotors.cars_service.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.cars_service.entities.CarEntity;
import br.com.globalmotors.cars_service.entities.dtos.CarRequestDTO;
import br.com.globalmotors.cars_service.repositories.CarRepo;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarRepo repo;

    public CarController(CarRepo repo) {
        this.repo = repo;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CarEntity>> getAll(){
    	List<CarEntity>cars = repo.findAll();
    	return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/{carId}")
    public ResponseEntity<CarEntity> getById(@PathVariable("carId") UUID Id) throws Exception{
    	CarEntity car = repo.findById(Id)
    			.orElseThrow(() -> new Exception("Carro não encontrado."));
    	
    	return ResponseEntity.ok(car);
    }
    
    @PostMapping
    public ResponseEntity<CarEntity>create(@RequestBody CarRequestDTO newCar) {
    	var completeCar = new CarEntity();
    	BeanUtils.copyProperties(newCar, completeCar);
    	CarEntity save = repo.save(completeCar);
    	return ResponseEntity.created(URI.create("/cars/" + save.getId())).body(save);
    }
    
    @PutMapping("/{carId}")
    public ResponseEntity<CarEntity> update(@PathVariable("carId") UUID Id, @RequestBody CarRequestDTO updatedCar) throws Exception {
    	CarEntity existingCar = repo.findById(Id)
    			.orElseThrow(() -> new Exception ("Carro não encontrado."));
    	
    	existingCar.setPrice(updatedCar.getPrice());
        existingCar.setCurrency(updatedCar.getCurrency());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setYear(updatedCar.getYear());
        existingCar.setCarType(updatedCar.getCarType());
        existingCar.setMileage(updatedCar.getMileage());
        existingCar.setBrand(updatedCar.getBrand());
        existingCar.setStore(updatedCar.getStore());
        existingCar.setWorldwideQuantity(updatedCar.getWorldwideQuantity());
        existingCar.setNationalQuantity(updatedCar.getNationalQuantity());
        existingCar.setColor(updatedCar.getColor());
        existingCar.setHorsePower(updatedCar.getHorsePower());
        existingCar.setCategory(updatedCar.getCategory());
        existingCar.setDoors(updatedCar.getDoors());
        existingCar.setTraction(updatedCar.getTraction());
        existingCar.setCarConfiguration(updatedCar.getCarConfiguration());
        existingCar.setShift(updatedCar.getShift());
        existingCar.setAccelerationToHundred(updatedCar.getAccelerationToHundred());
        existingCar.setTorque(updatedCar.getTorque());
        existingCar.setMotorization(updatedCar.getMotorization());
        existingCar.setPropulsion(updatedCar.getPropulsion());
        existingCar.setIsAvailable(updatedCar.getIsAvailable());
        existingCar.setDefaultPicture(updatedCar.getDefaultPicture());
    	
    	
    	repo.save(existingCar);
    	return ResponseEntity.ok(null);
    }
}