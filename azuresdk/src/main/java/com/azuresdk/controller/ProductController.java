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

import com.azuresdk.dtos.ProductDTO;
import com.azuresdk.exception.NameAlreadyExistsException;
import com.azuresdk.exception.NameDoesNotExistException;
import com.azuresdk.model.Product;
import com.azuresdk.repository.ProductRepository;
import com.azuresdk.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Operation(summary = "GET the requested product by name")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{name}")
    public ProductDTO get(@PathVariable String name) {
        if(!productRepository.existsByName(name))
            throw new NameDoesNotExistException(Product.class, name);
        return productService.get(name);
    }

    @Operation(summary = "GET the request page of products of the requested size")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<ProductDTO> getByPage(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getByPage(pageNumber, pageSize);
    }

    @Operation(summary = "DELETE the requested product by name")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        if(!productRepository.existsByName(name))
            throw new NameDoesNotExistException(Product.class, name);
        productService.delete(name);
    }

    @Operation(summary = "POST the requested product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void post(@Valid @RequestBody ProductDTO dto) {
        if(productRepository.existsByName(dto.getName()))
            throw new NameAlreadyExistsException(Product.class, dto.getName());
        productService.post(dto);
    }

    @Operation(summary = "PATCH the requested product by name")
     @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{name}")
    public void patchProduct(@PathVariable String name, @Valid @RequestBody ProductDTO dto) {
        if(!name.equals(dto.getName()) && productRepository.existsByName(dto.getName()))
            throw new NameAlreadyExistsException(Product.class, dto.getName());
        productService.patch(name, dto);
    }
    
}
