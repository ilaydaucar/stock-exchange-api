package com.stockexchange.stock_exchange_api.exceptions.stockexchange;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockExchangeNotFoundException extends RuntimeException {
    public StockExchangeNotFoundException(String name) {
        super("Stock exchange with id " + name + " not found!");
    }
    public StockExchangeNotFoundException(Long id) {
        super("Stock exchange with id " + id + " not found!");
    }
}
