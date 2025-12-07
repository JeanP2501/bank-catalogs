package com.bank.catalogs.service;

import com.bank.catalogs.model.entity.CustomerType;
import com.bank.catalogs.model.entity.DocumentType;
import com.bank.catalogs.model.entity.ProductType;
import com.bank.catalogs.model.entity.TransactionType;
import com.bank.catalogs.repository.CustomerTypeRepository;
import com.bank.catalogs.repository.DocumentTypeRepository;
import com.bank.catalogs.repository.ProductTypeRepository;
import com.bank.catalogs.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogService {

    private final CustomerTypeRepository customerTypeRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final ReactiveRedisTemplate<String, Object> redisTemplate;

    @Value("${cache.ttl.catalog}")
    private Long cacheTtl;

    private static final String CACHE_PREFIX = "catalog:";

    // ==================== CUSTOMER TYPES ====================

    public Mono<CustomerType> getCustomerTypeByCode(String code) {
        String cacheKey = CACHE_PREFIX + "customer_type:" + code;

        return redisTemplate.opsForValue()
                .get(cacheKey)
                .cast(CustomerType.class)
                .doOnNext(cached -> log.info("✅ Cache HIT: {}", cacheKey))
                .switchIfEmpty(
                        customerTypeRepository.findByCode(code)
                                .flatMap(customerType ->
                                        redisTemplate.opsForValue()
                                                .set(cacheKey, customerType, Duration.ofSeconds(cacheTtl))
                                                .thenReturn(customerType)
                                                .doOnSuccess(saved -> log.info("💾 Guardado en cache: {}", cacheKey))
                                )
                                .doOnNext(db -> log.info("🔍 Cache MISS, consultado de MongoDB"))
                );
    }

    public Flux<CustomerType> getAllCustomerTypes() {
        return customerTypeRepository.findAll();
    }

    // ==================== DOCUMENT TYPES ====================

    public Mono<DocumentType> getDocumentTypeByCode(String code) {
        String cacheKey = CACHE_PREFIX + "document_type:" + code;

        return redisTemplate.opsForValue()
                .get(cacheKey)
                .cast(DocumentType.class)
                .switchIfEmpty(
                        documentTypeRepository.findByCode(code)
                                .flatMap(documentType ->
                                        redisTemplate.opsForValue()
                                                .set(cacheKey, documentType, Duration.ofSeconds(cacheTtl))
                                                .thenReturn(documentType)
                                )
                );
    }

    public Flux<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    // ==================== PRODUCT TYPES ====================

    public Mono<ProductType> getProductTypeByCode(String code) {
        String cacheKey = CACHE_PREFIX + "product_type:" + code;

        return redisTemplate.opsForValue()
                .get(cacheKey)
                .cast(ProductType.class)
                .switchIfEmpty(
                        productTypeRepository.findByCode(code)
                                .flatMap(productType ->
                                        redisTemplate.opsForValue()
                                                .set(cacheKey, productType, Duration.ofSeconds(cacheTtl))
                                                .thenReturn(productType)
                                )
                );
    }

    public Flux<ProductType> getProductTypesByCategory(String category) {
        return productTypeRepository.findByProductCategory(category);
    }

    public Flux<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    // ==================== TRANSACTION TYPES ====================

    public Mono<TransactionType> getTransactionTypeByCode(String code) {
        String cacheKey = CACHE_PREFIX + "transaction_type:" + code;

        return redisTemplate.opsForValue()
                .get(cacheKey)
                .cast(TransactionType.class)
                .switchIfEmpty(
                        transactionTypeRepository.findByCode(code)
                                .flatMap(transactionType ->
                                        redisTemplate.opsForValue()
                                                .set(cacheKey, transactionType, Duration.ofSeconds(cacheTtl))
                                                .thenReturn(transactionType)
                                )
                );
    }

    public Flux<TransactionType> getAllTransactionTypes() {
        return transactionTypeRepository.findAll();
    }

}
