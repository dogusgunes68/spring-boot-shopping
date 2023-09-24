package com.example.springbootshopping.dao.log;

import com.example.springbootshopping.models.Log;
import com.example.springbootshopping.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("logRepo")
public class LogDaoService implements LogDao{

    private LogRepository logRepository;

    @Autowired
    public LogDaoService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    @Override
    public void insertLog(Log log) {
        logRepository.insert(log);
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> getLogsByUserId(UUID userId) {
        return logRepository.getLogsByUserId(userId);
    }

    @Override
    public List<Log> getLogsByRequestType(String requestType) {
        return logRepository.getLogsByRequestType(requestType);
    }
}
