package com.example.springbootshopping.dao.user;

import com.example.springbootshopping.models.User;
import com.example.springbootshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userRepo")
public class UserDaoService implements UserDao{

    private UserRepository userRepository;

    @Autowired
    public UserDaoService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(UUID id, User user) {
        User userToUpdate = userRepository.getReferenceById(id);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setName(user.getName());
        userToUpdate.setOrder(user.getOrder());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(userToUpdate);
    }

    @Override
    public UUID deleteUser(UUID id) {
        userRepository.deleteById(id);
        return id;
    }
}
