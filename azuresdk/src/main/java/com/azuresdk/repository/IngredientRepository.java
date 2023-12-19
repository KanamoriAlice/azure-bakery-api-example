package com.azuresdk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.azuresdk.model.Ingredient;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {

    Ingredient findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
    
}
