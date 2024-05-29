package com.example.awssecretsmanagerdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Map<String, String> databaseCredentials;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(databaseCredentials.get("jdbc:mysql://localhost:3306/mydatabase"));
        dataSource.setUsername(databaseCredentials.get("root"));
        dataSource.setPassword(databaseCredentials.get("admin123"));
        return dataSource;
    }
}