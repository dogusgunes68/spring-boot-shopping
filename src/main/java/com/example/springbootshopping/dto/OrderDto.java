package com.example.springbootshopping.dto;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class OrderDto {

    private UUID id;

    private List products;

    private Long totalPrice;

    private Date date;

    private UserDto user;
}
