package com.stockexchange.stock_exchange_api.model.dto;

import java.util.Set;

public class StockExchangeDTO {
    private Long id;
    private String name;
    private String description;
    private boolean liveInMarket;
    private Set<StockDTO> stocks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLiveInMarket() {
        return liveInMarket;
    }

    public void setLiveInMarket(boolean liveInMarket) {
        this.liveInMarket = liveInMarket;
    }

    public Set<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(Set<StockDTO> stocks) {
        this.stocks = stocks;
    }
}