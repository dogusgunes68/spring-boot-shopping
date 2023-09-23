package com.example.springbootshopping.repository;

import com.example.springbootshopping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
