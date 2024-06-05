package com.stockexchange.stock_exchange_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StockExchangeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockExchangeApiApplication.class, args);
	}

}
