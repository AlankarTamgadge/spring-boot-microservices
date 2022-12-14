package com.example.currencyexchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyexchange.bean.ExchangeValue;
import com.example.currencyexchange.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = repository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());

		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		System.out.println(exchangeValue);

		return exchangeValue;
	}

	@GetMapping("/currency-exchange/findall")
	public List<ExchangeValue> retrieveAllExchangeValue() {
		List<ExchangeValue> exchangeValues = repository.findAll();

		return exchangeValues;
	}
}