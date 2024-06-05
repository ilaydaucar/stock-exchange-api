package com.stockexchange.stock_exchange_api.exceptions.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Long id) {
        super("Stock with id " + id + " not found!");
    }

    public StockNotFoundException(String name) {
        super("Stock with name " + name + " not found!");
    }
}
