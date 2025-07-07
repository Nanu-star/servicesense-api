package com.nadd.servicesense_api.config;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
      @PostConstruct
    public void enablePgVector() {
        try (Connection conn = dataSource().getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE EXTENSION IF NOT EXISTS vector;");
        } catch (SQLException e) {
            e.printStackTrace(); // Consider replacing with a logger in production
        }
    }
}
