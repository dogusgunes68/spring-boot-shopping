package com.example.springbootshopping.dao.user;

import com.example.springbootshopping.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    void insertUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);
    User updateUser(UUID id, User user);
    UUID deleteUser(UUID id);
}
