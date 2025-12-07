package com.bank.catalogs.repository;

import com.bank.catalogs.model.entity.CustomerType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerTypeRepository extends ReactiveMongoRepository<CustomerType, String> {
    Mono<CustomerType> findByCode(String code);
}
