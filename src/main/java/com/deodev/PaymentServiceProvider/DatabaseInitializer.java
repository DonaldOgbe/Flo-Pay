package com.deodev.PaymentServiceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String sql = "SELECT 1";
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
            System.out.println("Database connection successful. Result: " + result);
        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}
