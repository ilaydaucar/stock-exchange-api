package com.stockexchange.stock_exchange_api.model.entity;

import jakarta.persistence.*;

@Entity
public class StockExchangeStockRelation {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_exchange_id")
    private StockExchange stockExchange;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public StockExchangeStockRelation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "StockExchangeStockRelation{" +
                "id=" + id +
                ", stockExchange=" + stockExchange +
                ", stock=" + stock +
                '}';
    }


}
