package com.example.springbootshopping.dao.log;

import com.example.springbootshopping.models.Log;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogDao {
    void insertLog(Log log);
    List<Log> getAllLogs();
    List<Log> getLogsByUserId(UUID userId);
    List<Log> getLogsByRequestType(String requestType);
}
