package br.com.globalmotors.currencies_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.globalmotors.currencies_service.entities.ExchangeRateEntity;
import br.com.globalmotors.currencies_service.entities.ExchangeRateId;
import br.com.globalmotors.currencies_service.models.Currency.Currencies;

@Repository
public interface CurrencyRepo extends JpaRepository<ExchangeRateEntity, ExchangeRateId>{
	Optional<ExchangeRateEntity> findByCurrencySourceAndCurrencyTarget(Currencies currencySource, Currencies currencyTarget);
}
