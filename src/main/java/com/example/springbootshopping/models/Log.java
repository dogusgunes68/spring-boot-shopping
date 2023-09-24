package com.example.springbootshopping.models;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class Log {
    @Id
    private String id;
    private String requestType;
    private String requestUrl;
    private UUID userId;
    private LocalDateTime createdDate;
}
