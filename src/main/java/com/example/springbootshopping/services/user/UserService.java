package com.example.springbootshopping.services.user;

import com.example.springbootshopping.dao.user.UserDao;
import com.example.springbootshopping.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;
    @Value("rabbitmq.exchange.name")
    private String exchange;

    @Value("rabbitmq.routing.user")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserService(@Qualifier("userRepo") UserDao userDao, RabbitTemplate rabbitTemplate){
        this.userDao = userDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addUser(User user){
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public Optional<User> getUserById(UUID id){
        return userDao.getUserById(id);
    }

    public User updateUser(UUID id, User user){
        return userDao.updateUser(id, user);
    }

    public UUID deleteUser(UUID id){
        return userDao.deleteUser(id);
    }

}
