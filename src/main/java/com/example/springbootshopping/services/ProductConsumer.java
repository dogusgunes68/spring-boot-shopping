package com.example.springbootshopping.services;

import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @Autowired
    private ProductRepository productRepository;

    @RabbitListener(queues = "${rabbitmq.queue.product}")
    public void consume(Product product){
        // add product to database
        try {
            productRepository.save(product);
            // logging
            ResponseEntity.status(200).body("başarılı");
        }catch (Exception e){
            throw e;
        }
    }
}
