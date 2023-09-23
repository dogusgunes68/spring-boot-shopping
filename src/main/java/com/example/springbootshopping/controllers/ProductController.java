package com.example.springbootshopping.controllers;

import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@NonNull @RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(201).body("Product added");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(productService.getProductById(id).get());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @NonNull @RequestBody Product product){
        return ResponseEntity.status(203).body(productService.updateProduct(id, product));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(productService.deleteProduct(id));
    }

}
