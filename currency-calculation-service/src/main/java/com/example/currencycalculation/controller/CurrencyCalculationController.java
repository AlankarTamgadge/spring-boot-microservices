package com.example.currencycalculation.controller;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.currencycalculation.facade.CurrencyExchangeProxy;
import com.example.currencycalculation.model.CalculatedAmount;

@RestController
public class CurrencyCalculationController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
	public CalculatedAmount calculateAmount(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CalculatedAmount> response = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CalculatedAmount.class, uriVariables);
		
		CalculatedAmount calculatedAmount = response.getBody();
		System.out.println(calculatedAmount);

		return new CalculatedAmount(calculatedAmount.getId(), calculatedAmount.getFrom(), calculatedAmount.getTo(),
				calculatedAmount.getConversionMultiple(), quantity,
				quantity.multiply(calculatedAmount.getConversionMultiple()), calculatedAmount.getPort());

	}

	@GetMapping("/currency-exchange-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CalculatedAmount calculateAmountFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		
		CalculatedAmount calculatedAmount = proxy.retrieveExchangeValue(from, to);

		return new CalculatedAmount(calculatedAmount.getId(), calculatedAmount.getFrom(), calculatedAmount.getTo(),
				calculatedAmount.getConversionMultiple(), quantity,
				quantity.multiply(calculatedAmount.getConversionMultiple()), calculatedAmount.getPort());

	}

}
