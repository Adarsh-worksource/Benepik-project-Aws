package com.example.awssecretsmanagerdemo.service;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SecretsManagerService {

    private final AtomicReference<Map<String, String>> credentials = new AtomicReference<>();

    public SecretsManagerService() {
        updateCredentials();
    }

    @Scheduled(fixedRate = 300000) // Update every 5 minutes
    public void updateCredentials() {
        String secretName = "mydatabase-credentials";
        String region = "ap-south-1";

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        String secretString = getSecretValueResult.getSecretString();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            credentials.set(objectMapper.readValue(secretString, Map.class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse secret", e);
        }
    }

    public Map<String, String> getCredentials() {
        return credentials.get();
    }
}