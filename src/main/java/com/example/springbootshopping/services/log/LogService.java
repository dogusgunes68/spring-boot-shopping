package com.example.springbootshopping.services.log;

import com.example.springbootshopping.dao.log.LogDao;
import com.example.springbootshopping.models.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LogService {
    private final LogDao logDao;

    @Value("rabbitmq.exchange.name")
    private String exchange;

    @Value("rabbitmq.routing.log")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public LogService(@Qualifier("logRepo") LogDao logDao, RabbitTemplate rabbitTemplate){
        this.logDao = logDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void insertLog(Log log){
        rabbitTemplate.convertAndSend(exchange, routingKey, log);
    }

    public List<Log> getAllLogs(){
        return logDao.getAllLogs();
    }

    public List<Log> getLogsByUserId(UUID userId){
        return logDao.getLogsByUserId(userId);
    }

    public List<Log> getLogsByRequestType(String requestType){
        return logDao.getLogsByRequestType(requestType);
    }

}
