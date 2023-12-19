package com.azuresdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.azuresdk.dtos.SupplierDTO;
import com.azuresdk.exception.NameAlreadyExistsException;
import com.azuresdk.exception.NameDoesNotExistException;
import com.azuresdk.model.Supplier;
import com.azuresdk.repository.SupplierRepository;
import com.azuresdk.service.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierRepository supplierRepository;

    @Operation(summary = "GET the requested supplier by name")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{name}")
    public SupplierDTO get(@PathVariable String name) {
        if(!supplierRepository.existsByName(name))
            throw new NameDoesNotExistException(Supplier.class, name);
        return supplierService.get(name);
    }

    @Operation(summary = "GET the request page of suppliers of the requested size")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public Page<SupplierDTO> getByPage(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return supplierService.getByPage(pageNumber, pageSize);
    }

    @Operation(summary = "DELETE the requested supplier by name")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        if(!supplierRepository.existsByName(name))
            throw new NameDoesNotExistException(Supplier.class, name);
        supplierService.delete(name);
    }

    @Operation(summary = "POST the requested supplier")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void post(@Valid @RequestBody SupplierDTO dto) {
        if(supplierRepository.existsByName(dto.getName()))
            throw new NameAlreadyExistsException(Supplier.class, dto.getName());
        supplierService.post(dto);
    }

    @Operation(summary = "PATCH the requested supplier by name")
     @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{name}")
    public void patchSupplier(@PathVariable String name, @Valid @RequestBody SupplierDTO dto) {
        if(!name.equals(dto.getName()) && supplierRepository.existsByName(dto.getName()))
            throw new NameAlreadyExistsException(Supplier.class, dto.getName());
        supplierService.patch(name, dto);
    }
    
}
