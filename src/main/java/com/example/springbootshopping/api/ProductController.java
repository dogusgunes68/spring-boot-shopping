package com.example.springbootshopping.api;

import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id){
        return ResponseEntity.status(200).body(productService.getProductById(id).get());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") UUID id, @NonNull @RequestBody Product product){
        return ResponseEntity.status(203).body(productService.updateProduct(id, product));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<UUID> deleteProduct(@PathVariable("id") UUID id){
        return ResponseEntity.status(200).body(productService.deleteProduct(id));
    }

}
