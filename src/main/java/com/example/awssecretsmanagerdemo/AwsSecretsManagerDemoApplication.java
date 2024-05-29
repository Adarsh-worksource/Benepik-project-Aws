package com.example.awssecretsmanagerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AwsSecretsManagerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSecretsManagerDemoApplication.class, args);
	}

}
