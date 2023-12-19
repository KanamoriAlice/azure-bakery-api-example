package com.azuresdk.dtos;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SupplierDTO {

    private String name;
    private String contactNumber;
    private List<String> suppliedIngredients;
    
}
