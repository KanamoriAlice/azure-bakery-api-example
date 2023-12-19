package com.azuresdk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.azuresdk.model.Supplier;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {

    Supplier findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
    
}
