package com.bank.catalogs.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "transaction_types")
public class TransactionType {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private String affectsBalance;
    private Boolean active;
    private LocalDateTime createdAt;
}
