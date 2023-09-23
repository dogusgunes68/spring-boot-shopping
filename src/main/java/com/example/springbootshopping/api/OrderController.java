package com.example.springbootshopping.api;

import com.example.springbootshopping.models.Order;
import com.example.springbootshopping.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> addOrder(@NonNull @RequestBody Order order){
        orderService.addOrder(order);
        return ResponseEntity.status(201).body("Order inserted");
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") UUID id){
        return ResponseEntity.status(200).body(orderService.getOrderById(id).get());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") UUID id, @NonNull @RequestBody Order order){
        return ResponseEntity.status(203).body(orderService.updateOrder(id,order));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<UUID> deleteOrder(@PathVariable("id") UUID id){
        return ResponseEntity.status(200).body(orderService.deleteOrder(id));
    }

    // GET ORDER DETAILS (JOIN TABLES)

}
