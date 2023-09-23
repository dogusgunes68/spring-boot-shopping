package com.example.springbootshopping.services.product;

import com.example.springbootshopping.dao.product.ProductDao;
import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @Qualifier("productRepo")
    private ProductDao productDao;

    @RabbitListener(queues = "${rabbitmq.queue.product}")
    public void consume(Product product){
        // add product to database
        try {
            productDao.insertProduct(product);
            // logging
        }catch (Exception e){
            throw e;
        }
    }
}
