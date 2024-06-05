package com.stockexchange.stock_exchange_api.repository;

import com.stockexchange.stock_exchange_api.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByName(String name);
}
