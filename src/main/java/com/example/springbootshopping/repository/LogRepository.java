package com.example.springbootshopping.repository;

import com.example.springbootshopping.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface LogRepository extends MongoRepository<Log, String> {
    List<Log> getLogsByUserId(UUID userId);
    List<Log> getLogsByRequestType(String requestType);
}
