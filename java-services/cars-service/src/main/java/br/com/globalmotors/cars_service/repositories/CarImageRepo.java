package br.com.globalmotors.cars_service.repositories;

import org.springframework.stereotype.Repository;

import br.com.globalmotors.cars_service.entities.CarImageEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarImageRepo extends JpaRepository<CarImageEntity, UUID>{

}
