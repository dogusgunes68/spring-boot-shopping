package com.example.springbootshopping.dao.product;

import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("productRepo")
public class ProductDaoService implements ProductDao {

    private ProductRepository productRepository;

    @Autowired
    public ProductDaoService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void insertProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(UUID id, Product product) {
        Product productToUpdate = productRepository.getReferenceById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());

        return productRepository.save(productToUpdate);
    }

    @Override
    public UUID deleteProduct(UUID id) {
        productRepository.deleteById(id);
        return id;
    }
}
