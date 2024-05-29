package com.example.awssecretsmanagerdemo.repository;

import com.example.awssecretsmanagerdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
