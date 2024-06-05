package com.stockexchange.stock_exchange_api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class StockExchange {
    public static final int LIVE_IN_MARKET_THRESHOLD = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 25)
    @NotNull
    private String name;
    @Column(nullable = false, length = 255)
    @NotNull
    private String description;
    @NotNull
    private boolean liveInMarket = false;

    @OneToMany(mappedBy = "stockExchange", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<StockExchangeStockRelation> stockExchangeStockRelations = new HashSet<>();;

    public StockExchange() {
    }

    public StockExchange(String name, String description, boolean liveInMarket) {
        this.name = name;
        this.description = description;
        this.liveInMarket = liveInMarket;
    }
    // Getters and setters

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

    public Set<StockExchangeStockRelation> getStockExchangeStockRelations() {
        return stockExchangeStockRelations;
    }

    public void setStockExchangeStockRelations(Set<StockExchangeStockRelation> stockExchangeStockRelations) {
        this.stockExchangeStockRelations = stockExchangeStockRelations;
    }

    @Override
    public String toString() {
        return "StockExchange{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", liveInMarket=" + liveInMarket +
                '}';
    }
}
