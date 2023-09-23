package com.example.springbootshopping.services.product;

import com.example.springbootshopping.dao.product.ProductDao;
import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.repository.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.product}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ProductService(@Qualifier("productRepo")ProductDao productDao, RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.productDao = productDao;
    }

    public void addProduct(Product product){
        rabbitTemplate.convertAndSend(exchange, routingKey, product);
    }

    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }

    public Optional<Product> getProductById(UUID id){
        return productDao.getProductById(id);
    }

    public Product updateProduct(UUID id, Product product){
        return productDao.updateProduct(id, product);
    }

    public UUID deleteProduct(UUID id){
        return productDao.deleteProduct(id);
    }

}
