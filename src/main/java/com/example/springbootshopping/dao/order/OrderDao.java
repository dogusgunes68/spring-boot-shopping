package com.example.springbootshopping.dao.order;

import com.example.springbootshopping.models.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDao {
    void insertOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(UUID id);
    Order updateOrder(UUID id, Order order);
    UUID deleteOrder(UUID id);
}
