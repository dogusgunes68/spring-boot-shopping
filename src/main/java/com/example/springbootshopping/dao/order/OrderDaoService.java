package com.example.springbootshopping.dao.order;

import com.example.springbootshopping.models.Order;
import com.example.springbootshopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("orderRepo")
public class OrderDaoService implements OrderDao{


    private OrderRepository orderRepository;

    @Autowired
    public OrderDaoService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public void insertOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order updateOrder(UUID id, Order order) {
        Order orderToUpdate = orderRepository.getReferenceById(id);
        orderToUpdate.setDate(order.getDate());
        orderToUpdate.setUser(order.getUser());
        orderToUpdate.setProducts(order.getProducts());
        orderToUpdate.setTotalPrice(order.getTotalPrice());
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public UUID deleteOrder(UUID id) {
        orderRepository.deleteById(id);
        return id;
    }
}
