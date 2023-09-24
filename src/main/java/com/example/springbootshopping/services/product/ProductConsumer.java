package com.example.springbootshopping.services.product;

import com.example.springbootshopping.dao.product.ProductDao;
import com.example.springbootshopping.models.Product;
import com.example.springbootshopping.repository.ProductRepository;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    private ProductDao productDao;

    @Autowired
    public ProductConsumer(@Qualifier("productRepo") ProductDao productDao){
        this.productDao = productDao;
    }

    @RabbitListener(queues = "${rabbitmq.queue.product}")
    public void consume(Product product, Channel channel, @Header("amqp_deliveryTag") long deliveryTag) throws Exception{
        // add product to database
        try {
            productDao.insertProduct(product);
            channel.basicAck(deliveryTag, false);
            // logging
        }catch (Exception e){
            channel.basicNack(deliveryTag,false,true);
            throw e;
        }
    }
}
