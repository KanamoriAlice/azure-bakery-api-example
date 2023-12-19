package com.azuresdk.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTO {

    private String supplierName;
    private String name;
    private int quantity;
    private int restockLevel;
    
}
