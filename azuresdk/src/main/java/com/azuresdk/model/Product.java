package com.azuresdk.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private double price;
    private String category;
    private List<String> ingredients;
    private boolean isAvailable;

    public Product(String name, double price, String category, List<String> ingredients, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.ingredients = ingredients;
        this.isAvailable = isAvailable;
    }
    
}
