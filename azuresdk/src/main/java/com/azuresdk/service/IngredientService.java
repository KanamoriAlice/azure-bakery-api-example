package com.azuresdk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.azuresdk.dtos.IngredientDTO;
import com.azuresdk.model.Ingredient;
import com.azuresdk.model.Supplier;
import com.azuresdk.repository.IngredientRepository;
import com.azuresdk.repository.SupplierRepository;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SupplierRepository supplierRepository;
    

    public IngredientDTO get(String name) {
        return modelMapper.map(ingredientRepository.findByName(name), IngredientDTO.class);
    }

    public Page<IngredientDTO> getByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);
        return ingredients.map(x -> modelMapper.map(x, IngredientDTO.class));
    }

    public void delete(String name) {
        ingredientRepository.deleteByName(name);
    }

    public void post(IngredientDTO dto) {
        Supplier supplier = supplierRepository.findByName(dto.getSupplierName());
        ingredientRepository.save(new Ingredient(supplier.getId(), dto.getName(),
        dto.getQuantity(), dto.getRestockLevel()));
    }

    public void patch(String name, IngredientDTO dto) {
        Supplier supplier = supplierRepository.findByName(dto.getSupplierName());
        Ingredient ingredient = ingredientRepository.findByName(name);
        ingredient.setSupplierId(supplier.getId());
        ingredient.setName(dto.getName());
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setRestockLevel(dto.getRestockLevel());
        ingredientRepository.save(ingredient);
    }
    
}
