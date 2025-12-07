package com.bank.catalogs.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document(collection = "product_types")
public class ProductType {
    @Id
    private String id;
    private String code;
    private String productCategory;
    private String name;
    private Map<String, Object> properties;
    private Map<String, Map<String, Object>> customerTypeRules;
    private Boolean active;
    private LocalDateTime createdAt;
}
