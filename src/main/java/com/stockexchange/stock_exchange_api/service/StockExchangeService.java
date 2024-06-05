package com.stockexchange.stock_exchange_api.service;

import com.stockexchange.stock_exchange_api.exceptions.stockexchange.StockExchangeNotFoundException;
import com.stockexchange.stock_exchange_api.model.dto.StockDTO;
import com.stockexchange.stock_exchange_api.model.dto.StockExchangeDTO;
import com.stockexchange.stock_exchange_api.model.entity.Stock;
import com.stockexchange.stock_exchange_api.model.entity.StockExchange;
import com.stockexchange.stock_exchange_api.model.entity.StockExchangeStockRelation;
import com.stockexchange.stock_exchange_api.repository.StockExchangeRepository;
import com.stockexchange.stock_exchange_api.repository.StockExchangeStockRelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;
    private final StockService stockService;
    private final StockExchangeStockRelationRepository stockExchangeStockRelationRepository;

    public StockExchangeService(StockExchangeRepository stockExchangeRepository, StockService stockService, StockExchangeStockRelationRepository stockExchangeStockRelationRepository) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.stockService = stockService;
        this.stockExchangeStockRelationRepository = stockExchangeStockRelationRepository;
    }

    public List<StockExchange> getAllStockExchanges() {
        return stockExchangeRepository.findAll();
    }

    public StockExchange getStockExchangeByName(String name) {
        return stockExchangeRepository.findByName(name)
                .orElseThrow(() -> new StockExchangeNotFoundException(name));
    }

    public StockExchange createStockExchange(StockExchange stockExchange){
        stockExchange.setLiveInMarket(false);
        return stockExchangeRepository.save(stockExchange);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public StockExchange addStockToStockExchange(String stockExchangeName, String stockName) {
        StockExchange stockExchange = stockExchangeRepository.findByName(stockExchangeName)
                .orElseThrow(() -> new StockExchangeNotFoundException(stockExchangeName));

        Stock stock = stockService.findByName(stockName);
        StockExchangeStockRelation relation = new StockExchangeStockRelation();
        relation.setStockExchange(stockExchange);
        relation.setStock(stock);
        stockExchange.getStockExchangeStockRelations().add(relation);
        stockExchange.setLiveInMarket(!isLiveInMarketAllowed(stockExchange));
        stockExchangeRepository.save(stockExchange);
        return stockExchange;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public StockExchange deleteStockFromStockExchange(String stockExchangeName, String stockName){
        StockExchange stockExchange = findStockExchangeByName(stockExchangeName);
        Stock stock = stockService.findByName(stockName);
        StockExchangeStockRelation relation = stockExchangeStockRelationRepository.findByStockExchangeAndStock(stockExchange, stock)
                .orElseThrow(() -> new StockExchangeNotFoundException(stock.getName()));

        stockExchange.getStockExchangeStockRelations().remove(relation);
        stockExchangeStockRelationRepository.delete(relation);
        stockExchange.setLiveInMarket(!isLiveInMarketAllowed(stockExchange));
        stockExchangeRepository.save(stockExchange);
        return stockExchange;
    }

    public boolean isLiveInMarketAllowed(StockExchange stockExchange) {
         return stockExchange.getStockExchangeStockRelations().size() < stockExchange.LIVE_IN_MARKET_THRESHOLD;
    }

    public void deleteStockExchange(Long id) {
        stockExchangeRepository.deleteById(id);
    }

    public StockExchange findStockExchangeByName(String name) {
        return stockExchangeRepository.findByName(name)
                .orElseThrow(() -> new StockExchangeNotFoundException(name));
    }

    public StockExchangeDTO convertToDTO(StockExchange stockExchange) {
        StockExchangeDTO dto = new StockExchangeDTO();
        dto.setId(stockExchange.getId());
        dto.setName(stockExchange.getName());
        dto.setDescription(stockExchange.getDescription());
        dto.setLiveInMarket(stockExchange.isLiveInMarket());

        Set<StockDTO> stockDTOs = stockExchange.getStockExchangeStockRelations().stream()
                .map(rel -> {
                    StockDTO stockDTO = new StockDTO();
                    stockDTO.setId(rel.getStock().getId());
                    stockDTO.setName(rel.getStock().getName());
                    stockDTO.setDescription(rel.getStock().getDescription());
                    stockDTO.setCurrentPrice(rel.getStock().getCurrentPrice());
                    stockDTO.setLastUpdate(rel.getStock().getLastUpdate());
                    return stockDTO;
                })
                .collect(Collectors.toSet());

        dto.setStocks(stockDTOs);

        return dto;
    }
}
