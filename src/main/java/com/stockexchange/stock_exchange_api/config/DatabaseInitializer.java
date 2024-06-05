package com.stockexchange.stock_exchange_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;


@Component
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initializeDatabase() {
        try {
            // Load SQL script file
            ClassPathResource resource = new ClassPathResource("/scripts/data.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String script = reader.lines().collect(Collectors.joining("\n"));
            reader.close();

            // Execute script
            jdbcTemplate.execute(script);
            System.out.println("SQL script executed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}