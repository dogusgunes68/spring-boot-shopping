package com.example.springbootshopping.dto;

import com.example.springbootshopping.models.Order;
import com.example.springbootshopping.models.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserDto {
    private UUID id;

    private String name;

    private String email;
    private String password;
    private Order order;

}
