package com.example.springbootshopping.services.order;

import com.example.springbootshopping.dao.order.OrderDao;
import com.example.springbootshopping.models.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private OrderDao orderDao;

    @Autowired
    public OrderConsumer(@Qualifier("orderRepo") OrderDao orderDao){
        this.orderDao = orderDao;
    }

    @RabbitListener(queues = "${rabbitmq.queue.order}")
    public void consume(Order order, Channel channel, @Header(name = "amqp_deliveryTag") long deliveryTag) throws Exception{
        // add order to database
        try {
            orderDao.insertOrder(order);
            channel.basicAck(deliveryTag, false);
            // logging
        }catch (Exception e){
            channel.basicNack(deliveryTag, false, true);
            throw e;
        }
    }
}
