package com.stockexchange.stock_exchange_api.exceptions.stockexchange;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExchangeAlreadyExistsException extends RuntimeException {

    public ExchangeAlreadyExistsException(String exchangeName, String stockName) {
        super("Mapping already exists for Stock: " + stockName + " and StockExchange: " + exchangeName);
    }
}
