package com.azuresdk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.azuresdk.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
    
}
