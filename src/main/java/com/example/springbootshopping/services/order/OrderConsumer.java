package com.example.springbootshopping.services.order;

import com.example.springbootshopping.dao.order.OrderDao;
import com.example.springbootshopping.models.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @Qualifier("orderRepo")
    private OrderDao orderDao;

    @RabbitListener(queues = "${rabbitmq.queue.order}")
    public void consume(Order order){
        // add product to database
        try {
            orderDao.insertOrder(order);
            // logging
        }catch (Exception e){
            throw e;
        }
    }
}
