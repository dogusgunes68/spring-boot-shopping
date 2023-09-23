package com.example.springbootshopping.services.order;

import com.example.springbootshopping.dao.order.OrderDao;
import com.example.springbootshopping.dao.product.ProductDao;
import com.example.springbootshopping.models.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderDao orderDao;

    @Value("rabbitmq.exchange.name")
    private String exchange;

    @Value("rabbitmq.routing.order")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderService(@Qualifier("orderRepo")OrderDao orderDao, RabbitTemplate rabbitTemplate){
        this.orderDao = orderDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addOrder(Order order){
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
    }

    public List<Order> getAllOrders(){
        return orderDao.getAllOrders();
    }

    public Optional<Order> getOrderById(UUID id){
        return orderDao.getOrderById(id);
    }

    public Order updateOrder(UUID id, Order order){
        return orderDao.updateOrder(id, order);
    }

    public UUID deleteOrder(UUID id){
        return orderDao.deleteOrder(id);
    }
}
