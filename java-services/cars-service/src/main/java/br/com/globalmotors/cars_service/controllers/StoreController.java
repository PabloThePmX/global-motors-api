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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalmotors.cars_service.entities.StoreEntity;
import br.com.globalmotors.cars_service.entities.dtos.StoreDTO;
import br.com.globalmotors.cars_service.repositories.StoreRepo;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("cars/store")
public class StoreController {
	private final StoreRepo repo;

	public StoreController(StoreRepo repo) {
		this.repo = repo;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<StoreEntity>> getAll() {
		List<StoreEntity> stores = repo.findAll();
		return ResponseEntity.ok(stores);
	}
	
	@GetMapping("/{id_loja}")
	public ResponseEntity<StoreEntity>getById(@PathVariable("storeId") UUID id) throws Exception {
		StoreEntity store = repo.findById(id)
				.orElseThrow(() -> new Exception ("Loja não encontrada."));
		
		return ResponseEntity.ok(store);
	}
	
	@PostMapping
	public ResponseEntity<StoreEntity> create(@RequestBody StoreDTO newStore) {
		var completeStore = new StoreEntity();
		BeanUtils.copyProperties(newStore, completeStore);
		StoreEntity save = repo.save(completeStore);
		return ResponseEntity.created(URI.create("cars/store/ " + save.getId())).body(save);
	}
	
	@PutMapping("/{storeId}")
	public ResponseEntity<StoreEntity> update (@PathVariable("storeId") UUID Id, @RequestBody StoreEntity updateStore) throws Exception {
		StoreEntity existingStore = repo.findById(Id)
				.orElseThrow(() -> new Exception("Loja não encontrada"));
		existingStore.setName(updateStore.getName());
		existingStore.setOwner(updateStore.getOwner());
		existingStore.setPhoneNumber(updateStore.getPhoneNumber());
		existingStore.setAddress(updateStore.getAddress());
		existingStore.setDescription(updateStore.getDescription());
		existingStore.setEmail(updateStore.getEmail());
		existingStore.setWebsite(updateStore.getWebsite());
		
		repo.save(existingStore);
		
		return ResponseEntity.ok(null);
	}
	
	
}
