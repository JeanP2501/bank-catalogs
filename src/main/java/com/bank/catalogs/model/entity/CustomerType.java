package com.bank.catalogs.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "customer_types")
public class CustomerType {
    @Id
    private String id;
    private String code;
    private String name;
    private Boolean requiresCreditCard;
    private Integer minCreditCards;
    private Boolean active;
    private LocalDateTime createdAt;
}
