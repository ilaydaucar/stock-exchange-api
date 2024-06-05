package com.stockexchange.stock_exchange_api.controller;

import com.stockexchange.stock_exchange_api.model.dto.StockDTO;
import com.stockexchange.stock_exchange_api.model.entity.Stock;
import com.stockexchange.stock_exchange_api.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @PostMapping("/")
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    @GetMapping("/{stockName}")
    public StockDTO getStockById(@PathVariable String stockName) {
        return stockService.convertStockDTO(stockService.findByName(stockName));
    }

    @PutMapping("/{stockName}")
    public Stock updateStock(@PathVariable String stockName, @RequestBody Stock newStock) {
        return stockService.updateStock(stockName, newStock);
    }

    @PutMapping("/{stockName}/price")
    public ResponseEntity<String> updateStockPrice(@PathVariable String stockName, @RequestParam("price") double price) {
        stockService.updateStockPrice(stockName, price);
        return ResponseEntity.ok().body("Stock price updated successfully");
    }

    @DeleteMapping("/{stockName}")
    public ResponseEntity<String> deleteStock(@PathVariable String stockName) {
        stockService.deleteStock(stockName);
        return ResponseEntity.ok().body("Stock deleted successfully");
    }
}
