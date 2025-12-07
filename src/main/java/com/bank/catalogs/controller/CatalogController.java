package com.bank.catalogs.controller;

import com.bank.catalogs.model.entity.CustomerType;
import com.bank.catalogs.model.entity.DocumentType;
import com.bank.catalogs.model.entity.ProductType;
import com.bank.catalogs.model.entity.TransactionType;
import com.bank.catalogs.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    // ==================== CUSTOMER TYPES ====================

    @GetMapping("/customer-types")
    public Flux<CustomerType> getAllCustomerTypes() {
        return catalogService.getAllCustomerTypes();
    }

    @GetMapping("/customer-types/{code}")
    public Mono<ResponseEntity<CustomerType>> getCustomerTypeByCode(@PathVariable String code) {
        return catalogService.getCustomerTypeByCode(code)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // ==================== DOCUMENT TYPES ====================

    @GetMapping("/document-types")
    public Flux<DocumentType> getAllDocumentTypes() {
        return catalogService.getAllDocumentTypes();
    }

    @GetMapping("/document-types/{code}")
    public Mono<ResponseEntity<DocumentType>> getDocumentTypeByCode(@PathVariable String code) {
        return catalogService.getDocumentTypeByCode(code)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // ==================== PRODUCT TYPES ====================

    @GetMapping("/product-types")
    public Flux<ProductType> getAllProductTypes() {
        return catalogService.getAllProductTypes();
    }

    @GetMapping("/product-types/{code}")
    public Mono<ResponseEntity<ProductType>> getProductTypeByCode(@PathVariable String code) {
        return catalogService.getProductTypeByCode(code)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/product-types/category/{category}")
    public Flux<ProductType> getProductTypesByCategory(@PathVariable String category) {
        return catalogService.getProductTypesByCategory(category);
    }

    // ==================== TRANSACTION TYPES ====================

    @GetMapping("/transaction-types")
    public Flux<TransactionType> getAllTransactionTypes() {
        return catalogService.getAllTransactionTypes();
    }

    @GetMapping("/transaction-types/{code}")
    public Mono<ResponseEntity<TransactionType>> getTransactionTypeByCode(@PathVariable String code) {
        return catalogService.getTransactionTypeByCode(code)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
