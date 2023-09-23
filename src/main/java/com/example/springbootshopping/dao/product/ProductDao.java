package com.example.springbootshopping.dao.product;

import com.example.springbootshopping.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao {
    void insertProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);
    Product updateProduct(UUID id, Product product);
    UUID deleteProduct(UUID id);
}
