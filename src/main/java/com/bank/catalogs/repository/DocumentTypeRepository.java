package com.bank.catalogs.repository;

import com.bank.catalogs.model.entity.DocumentType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DocumentTypeRepository extends ReactiveMongoRepository<DocumentType, String> {
    Mono<DocumentType> findByCode(String code);
}
