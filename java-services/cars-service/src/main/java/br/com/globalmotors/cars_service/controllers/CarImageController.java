package br.com.globalmotors.cars_service.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.cars_service.entities.CarImageEntity;
import br.com.globalmotors.cars_service.entities.dtos.CarImageRequestDTO;
import br.com.globalmotors.cars_service.entities.dtos.CarImageUpdateDTO;
import br.com.globalmotors.cars_service.repositories.CarImageRepo;

@RestController
@RequestMapping("cars/car-images/")
public class CarImageController {
	private final CarImageRepo repo;

	public CarImageController(CarImageRepo repo) {
		super();
		this.repo = repo;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CarImageEntity>> getAll() {
		List<CarImageEntity> images = repo.findAll();
		return ResponseEntity.ok(images);
	}
	
	@GetMapping("/{carId}")
	public ResponseEntity<List<CarImageEntity>> getByCar(@PathVariable ("carId") UUID car) throws Exception {
		List<CarImageEntity> images = repo.findByCar(car)
				.filter(list -> !list.isEmpty())
				.orElseThrow (() -> new Exception ("Carro não encontrado ou sem imagens disponíveis."));
		
		return ResponseEntity.ok(images); 
	}
	
	@GetMapping("/{carId}/{imageId}")
	public ResponseEntity<CarImageEntity> getById(@PathVariable ("carId") UUID car, @PathVariable ("imageId") int id) throws Exception {
		CarImageEntity existingImage = repo.findByIdAndCar(id, car)
				.orElseThrow (() -> new Exception ("Imagem não encontrada."));
		
		return ResponseEntity.ok(existingImage);
	}
	
	@PostMapping 
	public ResponseEntity<CarImageEntity> create(@RequestBody CarImageRequestDTO newImage) {
		var completeImage = new CarImageEntity();
		BeanUtils.copyProperties(newImage, completeImage);
		CarImageEntity save = repo.save(completeImage);
		return ResponseEntity.created(URI.create("/cars/car-images/" + save.getId())).body(save);
	}
	
	@PutMapping("/{carId}/{imageId}")
	public ResponseEntity<CarImageEntity> update (@PathVariable ("carId") UUID car, @PathVariable ("imageId") int id, @RequestBody CarImageUpdateDTO updateImage) throws Exception {
		CarImageEntity existingImage = repo.findByIdAndCar(id, car)
				.orElseThrow (() -> new Exception ("Imagem não encontrada."));
		
		existingImage.setImage(updateImage.getImage());
		
		repo.save(existingImage);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/{carId}/{imageId}")
	public ResponseEntity<CarImageEntity> delete (@PathVariable ("carId") UUID car, @PathVariable ("imageId") int id) throws Exception{
		CarImageEntity existingImage = repo.findByIdAndCar(id, car)
				.orElseThrow (() -> new Exception ("Imagem não encontrada."));
		
		repo.delete(existingImage);
		
		return ResponseEntity.ok(null);	
	}
}
