package com.stockexchange.stock_exchange_api.service;

import com.stockexchange.stock_exchange_api.exceptions.stock.StockNotFoundException;
import com.stockexchange.stock_exchange_api.model.entity.Stock;
import com.stockexchange.stock_exchange_api.model.dto.StockDTO;
import com.stockexchange.stock_exchange_api.model.entity.StockExchange;
import com.stockexchange.stock_exchange_api.model.entity.StockExchangeStockRelation;
import com.stockexchange.stock_exchange_api.repository.StockExchangeStockRelationRepository;
import com.stockexchange.stock_exchange_api.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockExchangeStockRelationRepository stockExchangeStockRelationRepository;

    public StockService(StockRepository stockRepository, StockExchangeStockRelationRepository stockExchangeStockRelationRepository) {
        this.stockRepository = stockRepository;
        this.stockExchangeStockRelationRepository = stockExchangeStockRelationRepository;
    }

    
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));
    }

    public Stock createStock(Stock stock) {
        stock.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        return stockRepository.save(stock);
    }
    
    public Stock updateStock(String name, Stock newStock) {
        return stockRepository.findByName(name)
                .map(stock -> {
                    stock.setName(newStock.getName());
                    stock.setDescription(newStock.getDescription());
                    stock.setCurrentPrice(newStock.getCurrentPrice());
                    stock.setLastUpdate(newStock.getLastUpdate());
                    return stockRepository.save(stock);
                })
                .orElseThrow(() -> new StockNotFoundException(name));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Stock updateStockPrice(String stockName, double newPrice) {
        Stock stock = findByName(stockName);
        stock.setCurrentPrice(BigDecimal.valueOf(newPrice));
        stock.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        return stockRepository.save(stock);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void deleteStock(String stockName) {

        Stock stock = stockRepository.findByName(stockName)
                .orElseThrow(() -> new StockNotFoundException(stockName));

        List<StockExchangeStockRelation> relations = stockExchangeStockRelationRepository.findByStock(stock);
        for (StockExchangeStockRelation relation : relations) {
            relation.getStockExchange().getStockExchangeStockRelations().remove(relation);
            stockExchangeStockRelationRepository.delete(relation);
        }
        stockRepository.delete(stock);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Stock findByName(String name) {
        return stockRepository.findByName(name)
                .orElseThrow(() -> new StockNotFoundException(name));
    }

    public StockDTO convertStockDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setName(stock.getName());
        stockDTO.setDescription(stock.getDescription());
        stockDTO.setCurrentPrice(stock.getCurrentPrice());
        stockDTO.setLastUpdate(stock.getLastUpdate());
        return stockDTO;
    }
}
