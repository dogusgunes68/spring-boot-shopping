package com.example.springbootshopping.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(targetEntity = Product.class)
    private List products;

    @NotNull
    private Long totalPrice;

    @NotNull
    private Date date;

    @OneToOne(targetEntity = User.class)
    private User user;

}
