package com.example.springbootshopping.api;

import com.example.springbootshopping.models.Log;
import com.example.springbootshopping.services.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/log")
public class LogController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService){
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<Log>> getAllLogs(){
        return ResponseEntity.status(200).body(logService.getAllLogs());
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Log>> getLogsByUserId(@PathVariable("userId")UUID userId){
        return ResponseEntity.status(200).body(logService.getLogsByUserId(userId));
    }

    @GetMapping(path = "{requestType}")
    public ResponseEntity<List<Log>> getLogsByRequestType(@PathVariable("requestType") String requestType){
        return ResponseEntity.status(200).body(logService.getLogsByRequestType(requestType));
    }

}
