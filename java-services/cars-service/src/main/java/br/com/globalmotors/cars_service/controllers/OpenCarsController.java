package br.com.globalmotors.cars_service.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.globalmotors.cars_service.entities.CarsEntity;
import br.com.globalmotors.cars_service.repositories.CarsRepo;

@RestController
@RequestMapping("cars")
public class OpenCarsController {

    private final CarsRepo repo;

    public OpenCarsController(CarsRepo repo) {
        this.repo = repo;
    }

    @Value("${server.port}")
    private int serverPort;

    @GetMapping
    public ResponseEntity<List<CarsEntity>> getAll() {
        List<CarsEntity> cars = repo.findAll();
        cars.forEach(car -> {
            car.setEnviroment("Car-Service running on port " + serverPort);
            car.setConvertedPrice(car.getConvertedPrice());
        });
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id_carro}")
    public ResponseEntity<CarsEntity> getById(@PathVariable("id_carro") Long id) throws Exception {
        CarsEntity car = repo.findById(id)
                .orElseThrow(() -> new Exception("Carro não encontrado."));
        car.setEnviroment("Car-Service running on port " + serverPort);
        car.setConvertedPrice(car.getConvertedPrice());
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarsEntity> create(@RequestBody CarsEntity newCar) {
        CarsEntity saved = repo.save(newCar);
        return ResponseEntity.created(URI.create("/cars/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id_carro}")
    public ResponseEntity<CarsEntity> update(@PathVariable("id_carro") Long id,
                                             @RequestBody CarsEntity updatedCar) throws Exception {
        CarsEntity existing = repo.findById(id)
                .orElseThrow(() -> new Exception("Carro não encontrado para atualização."));

        existing.setPrice(updatedCar.getPrice());
        existing.setCurrency(updatedCar.getCurrency());
        existing.setModel(updatedCar.getModel());
        existing.setYear(updatedCar.getYear());
        existing.setCarType(updatedCar.getCarType());
        existing.setMileage(updatedCar.getMileage());
        existing.setBrand(updatedCar.getBrand());
        existing.setStore(updatedCar.getStore());
        existing.setWorldwideQuantity(updatedCar.getWorldwideQuantity());
        existing.setNationalQuantity(updatedCar.getNationalQuantity());
        existing.setColor(updatedCar.getColor());
        existing.setHorsePower(updatedCar.getHorsePower());
        existing.setCategory(updatedCar.getCategory());
        existing.setDoors(updatedCar.getDoors());
        existing.setTraction(updatedCar.getTraction());
        existing.setCarConfiguration(updatedCar.getCarConfiguration());
        existing.setShift(updatedCar.getShift());
        existing.setAccelerationToHundred(updatedCar.getAccelerationToHundred());
        existing.setTorque(updatedCar.getTorque());
        existing.setMotorization(updatedCar.getMotorization());
        existing.setPropulsion(updatedCar.getPropulsion());
        existing.setAvailable(updatedCar.isAvailable());
        existing.setDefaultPicture(updatedCar.getDefaultPicture());

        CarsEntity saved = repo.save(existing);
        return ResponseEntity.ok(saved);
    }
}