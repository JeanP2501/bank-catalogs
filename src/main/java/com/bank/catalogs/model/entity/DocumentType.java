package com.bank.catalogs.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "document_types")
public class DocumentType {
    @Id
    private String id;
    private String code;
    private String name;
    private Integer limitDigits;
    private String pattern;
    private Boolean active;
    private LocalDateTime createdAt;
}
