package br.com.globalmotors.currencies_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.globalmotors.currencies_service.entities.ConversionRateEntity;
import br.com.globalmotors.currencies_service.models.Currency.currencies;

@Repository
public interface CurrencyRepo extends JpaRepository<ConversionRateEntity, currencies>{
	Optional<ConversionRateEntity> findByCurrency(currencies currency);
}
