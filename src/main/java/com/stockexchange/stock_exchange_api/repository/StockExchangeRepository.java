package com.stockexchange.stock_exchange_api.repository;

import com.stockexchange.stock_exchange_api.model.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange,Long> {
    Optional<StockExchange> findByName(String name);
}
