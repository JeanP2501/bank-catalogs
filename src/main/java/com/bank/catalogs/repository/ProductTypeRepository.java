package com.bank.catalogs.repository;

import com.bank.catalogs.model.entity.ProductType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductTypeRepository extends ReactiveMongoRepository<ProductType, String> {
    Mono<ProductType> findByCode(String code);
    Flux<ProductType> findByProductCategory(String category);
}
