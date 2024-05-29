package com.example.awssecretsmanagerdemo.config;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class SecretsManagerConfig {

    @Bean
    public Map<String, String> databaseCredentials() {
        String secretName = "mydatabase-credentials";
        String region = "ap-south-1";

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        String secretString = getSecretValueResult.getSecretString();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(secretString, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse secret", e);
        }
    }
}