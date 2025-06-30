package br.com.globalmotors.cars_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.globalmotors.cars_service.entities.CarsEntity;

@Repository
public interface CarsRepo extends JpaRepository<CarsEntity, Long>{

}
