package com.example.springbootshopping.repository;

import com.example.springbootshopping.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
