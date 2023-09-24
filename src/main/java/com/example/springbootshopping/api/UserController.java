package com.example.springbootshopping.api;

import com.example.springbootshopping.models.User;
import com.example.springbootshopping.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addOrder(@NonNull @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(201).body("User inserted");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllOrders(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> getOrderById(@PathVariable("id") UUID id){
        return ResponseEntity.status(200).body(userService.getUserById(id).get());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<User> updateOrder(@PathVariable("id") UUID id, @NonNull @RequestBody User user){
        return ResponseEntity.status(203).body(userService.updateUser(id,user));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<UUID> deleteOrder(@PathVariable("id") UUID id){
        return ResponseEntity.status(200).body(userService.deleteUser(id));
    }

}
