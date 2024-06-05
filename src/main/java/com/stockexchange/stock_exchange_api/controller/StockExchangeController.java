package com.stockexchange.stock_exchange_api.controller;

import com.stockexchange.stock_exchange_api.model.dto.StockExchangeDTO;
import com.stockexchange.stock_exchange_api.model.entity.StockExchange;
import com.stockexchange.stock_exchange_api.service.StockExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @GetMapping("/")
    public List<StockExchange> getAllStockExchanges() {
        return stockExchangeService.getAllStockExchanges();
    }

    @GetMapping("/{name}")
    public StockExchangeDTO getStockExchangeById(@PathVariable String name) {
        return stockExchangeService.convertToDTO(stockExchangeService.findStockExchangeByName(name));
    }

    @PostMapping("/")
    public StockExchange createStockExchange(@RequestBody StockExchange stockExchange) {
        return stockExchangeService.createStockExchange(stockExchange);
    }

    @PutMapping("/{stockExchangeName}/stock/{stockName}")
    public ResponseEntity<String> addStockToStockExchange(@PathVariable String stockExchangeName, @PathVariable String stockName) {
        stockExchangeService.addStockToStockExchange(stockExchangeName, stockName);
        return ResponseEntity.status(HttpStatus.CREATED).body("Stock added to StockExchange successfully");
    }


    @DeleteMapping("/{stockExchangeName}/stock/{stockName}")
    public ResponseEntity<String> deleteStockExchange(@PathVariable String stockExchangeName, @PathVariable String stockName) {
        stockExchangeService.deleteStockFromStockExchange(stockExchangeName, stockName);
        return ResponseEntity.status(HttpStatus.OK).body("Stock deleted from StockExchange successfully");
    }
}
