package com.example.springbootshopping.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


}
