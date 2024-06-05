package com.stockexchange.stock_exchange_api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 25)
    @NotNull
    private String name;
    @Column(nullable = false, length = 255)
    private String description;
    @NotNull
    private BigDecimal currentPrice;
    @NotNull
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<StockExchangeStockRelation> stockExchangeStockRelations = new HashSet<>();;

    public Stock() {
    }

    public Stock(String name, String description, BigDecimal currentPrice, Timestamp lastUpdate) {
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<StockExchangeStockRelation> getStockExchangeStockRelations() {
        return stockExchangeStockRelations;
    }

    public void setStockExchangeStockRelations(Set<StockExchangeStockRelation> stockExchangeStockRelations) {
        this.stockExchangeStockRelations = stockExchangeStockRelations;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentPrice=" + currentPrice +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
