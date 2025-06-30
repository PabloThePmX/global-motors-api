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

import br.com.globalmotors.cars_service.entities.BrandEntity;
import br.com.globalmotors.cars_service.entities.dtos.BrandDTO;
import br.com.globalmotors.cars_service.repositories.BrandsRepo;

@RestController
@RequestMapping("cars/brands")
public class BrandsController {
	
	private final BrandsRepo brandsRepo;
	
	public BrandsController(BrandsRepo brandsRepo) {
		this.brandsRepo = brandsRepo;
	}
	
	public ResponseEntity<List<BrandEntity>> getAll() {
		List<BrandEntity> brands = brandsRepo.findAll();
		return ResponseEntity.ok(brands);
	}
	
    @GetMapping("/{brandId}")
    public ResponseEntity<BrandEntity> getById(@PathVariable("brandId") UUID id) throws Exception {
        BrandEntity brand = brandsRepo.findById(id)
                .orElseThrow(() -> new Exception("Marca não encontrada."));

        return ResponseEntity.ok(brand);
    }

    @PostMapping
    public ResponseEntity<BrandEntity> create(@RequestBody BrandDTO newBrand) {
    	var completeBrand = new BrandEntity();
    	BeanUtils.copyProperties(newBrand, completeBrand);
    	BrandEntity saved = brandsRepo.save(completeBrand);
        return ResponseEntity.created(URI.create("/cars/brands/" + saved.getId())).body(saved);
    }
    
    @PutMapping("/{brandId}")
    public ResponseEntity<BrandEntity> update(@PathVariable("brandId") UUID id, @RequestBody BrandEntity updatedBrand) throws Exception {
        BrandEntity existingBrand = brandsRepo.findById(id)
            .orElseThrow(() -> new Exception("Marca não encontrada."));

        existingBrand.setName(updatedBrand.getName());
        existingBrand.setDescription(updatedBrand.getDescription());
        existingBrand.setCountryOrigin(updatedBrand.getCountryOrigin());
        existingBrand.setLogo(updatedBrand.getLogo());

        brandsRepo.save(existingBrand);

        return ResponseEntity.ok(null);
    }
}
