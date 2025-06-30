package br.com.globalmotors.cars_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.globalmotors.cars_service.entities.StoreEntity;

@Repository
public interface StoreRepo extends JpaRepository<StoreEntity, UUID>{

}
