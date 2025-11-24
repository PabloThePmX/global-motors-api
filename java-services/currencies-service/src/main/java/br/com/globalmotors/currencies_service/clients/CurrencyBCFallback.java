package br.com.globalmotors.currencies_service.clients;

import java.util.Collections;

import org.springframework.stereotype.Component;


@Component
public class CurrencyBCFallback implements CurrencyBCClient{

	@Override
	public CurrencyBCResponse getCurrency(String currency, String date) {
		CurrencyBCResponse fallback = new CurrencyBCResponse();
		fallback.setValue(Collections.emptyList());
		return fallback;
	}

}
