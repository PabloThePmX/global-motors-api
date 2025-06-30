package br.com.globalmotors.cars_service.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.cars_service.entities.CarImageEntity;
import br.com.globalmotors.cars_service.entities.dtos.CarImageDTO;
import br.com.globalmotors.cars_service.repositories.CarImageRepo;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	
	//TODO REVISAR
	@PostMapping 
	public ResponseEntity<CarImageEntity>create(@RequestBody CarImageDTO newImage) {
		var completeImage = new CarImageEntity();
		BeanUtils.copyProperties(newImage, completeImage);
		CarImageEntity save = repo.save(completeImage);
		return ResponseEntity.created(URI.create("/cars/car-images/" + save.getId())).body(save);
	}
	
	@PutMapping("/{id_carro}/{id_imagem}")
	public ResponseEntity<CarImageEntity> update (@PathVariable ("carImageId") UUID Id, @RequestBody CarImageEntity updateImage) throws Exception {
		CarImageEntity existingImage = repo.findById(Id)
				.orElseThrow (() -> new Exception ("Imagem não encontrada. "));
		existingImage.setImage(updateImage.getImage());
		existingImage.setCar(updateImage.getCar());
		
		repo.save(existingImage);
		return ResponseEntity.ok(null);
	}
	
	//TODO delete
}
