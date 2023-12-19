package com.azuresdk.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document(collection = "suppliers")
public class Supplier {

    @Id
    private String id;
    private String name;
    private String contactNumber;
    private List<String> suppliedIngredients;


    public Supplier(String name, String contactNumber, List<String> suppliedIngredients) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.suppliedIngredients = suppliedIngredients;
    }

    
}
