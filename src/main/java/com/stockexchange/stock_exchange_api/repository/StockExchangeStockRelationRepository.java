package com.stockexchange.stock_exchange_api.repository;


import com.stockexchange.stock_exchange_api.model.entity.Stock;
import com.stockexchange.stock_exchange_api.model.entity.StockExchange;
import com.stockexchange.stock_exchange_api.model.entity.StockExchangeStockRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockExchangeStockRelationRepository extends JpaRepository<StockExchangeStockRelation,Long> {
    Optional<StockExchangeStockRelation> findByStockExchangeAndStock(StockExchange stockExchange, Stock stock);
    List<StockExchangeStockRelation> findByStock(Stock stock);

}
