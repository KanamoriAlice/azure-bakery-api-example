package com.azuresdk.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private double price;
    private String category;
    private List<String> ingredients;
    private boolean isAvailable;
    
}
