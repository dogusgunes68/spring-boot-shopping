package com.example.springbootshopping.services;

import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.repository.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.product}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addProduct(Product product){
        rabbitTemplate.convertAndSend(exchange, routingKey, product);
    }

    public List<Product> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product product){
        Product productToUpdate = productRepository.getReferenceById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());

        return productRepository.save(productToUpdate);
    }

    public Long deleteProduct(Long id){
        productRepository.deleteById(id);
        return id;
    }

}
