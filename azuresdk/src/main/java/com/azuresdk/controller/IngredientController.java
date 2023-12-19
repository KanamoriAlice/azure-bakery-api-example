package com.azuresdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.azuresdk.dtos.IngredientDTO;
import com.azuresdk.exception.NameAlreadyExistsException;
import com.azuresdk.exception.NameDoesNotExistException;
import com.azuresdk.model.Ingredient;
import com.azuresdk.repository.IngredientRepository;
import com.azuresdk.service.IngredientService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Operation(summary = "GET the requested ingredient by name")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{name}")
    public IngredientDTO get(@PathVariable String name) {
        if(!ingredientRepository.existsByName(name))
            throw new NameDoesNotExistException(Ingredient.class, name);
        return ingredientService.get(name);
    }

    @Operation(summary = "GET the request page of ingredients of the requested size")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<IngredientDTO> getByPage(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return ingredientService.getByPage(pageNumber, pageSize);
    }

    @Operation(summary = "DELETE the requested ingredient by name")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        if(!ingredientRepository.existsByName(name))
            throw new NameDoesNotExistException(Ingredient.class, name);
        ingredientService.delete(name);
    }

    @Operation(summary = "POST the requested ingredient")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void post(@Valid @RequestBody IngredientDTO dto) {
        if(ingredientRepository.existsByName(dto.getName()))
            throw new NameAlreadyExistsException(Ingredient.class, dto.getName());
        ingredientService.post(dto);
    }

    @Operation(summary = "PATCH the requested ingredient by name")
     @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{name}")
    public void patchIngredient(@PathVariable String name, @Valid @RequestBody IngredientDTO dto) {
        if(!name.equals(dto.getName()) && ingredientRepository.existsByName(dto.getName()))
            throw new NameAlreadyExistsException(Ingredient.class, dto.getName());
        ingredientService.patch(name, dto);
    }
    
}
