package com.bank.catalogs.repository;

import com.bank.catalogs.model.entity.TransactionType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TransactionTypeRepository
        extends ReactiveMongoRepository<TransactionType, String> {

    Mono<TransactionType> findByCode(String code);

}
