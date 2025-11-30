package br.com.globalmotors.cars_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.globalmotors.cars_service.entities.CarEntity;

@Repository
public interface CarRepo extends JpaRepository<CarEntity, UUID>{
	
}
