package com.azuresdk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document(collection = "inventory")
public class Ingredient {

    @Id
    private String id;
    private String supplierId;
    private String name;
    private int quantity;
    private int restockLevel;

    public Ingredient(String supplierId, String name, int quantity, int restockLevel) {
        this.supplierId = supplierId;
        this.name = name;
        this.quantity = quantity;
        this.restockLevel = restockLevel;
    }

    
}
