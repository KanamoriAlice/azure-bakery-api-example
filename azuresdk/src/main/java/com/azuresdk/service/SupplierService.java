package com.azuresdk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.azuresdk.dtos.SupplierDTO;
import com.azuresdk.model.Supplier;
import com.azuresdk.repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ModelMapper modelMapper;

    public SupplierDTO get(String name) {
        return modelMapper.map(supplierRepository.findByName(name), SupplierDTO.class);
    }

    public Page<SupplierDTO> getByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Supplier> suppliers = supplierRepository.findAll(pageable);
        return suppliers.map(x -> modelMapper.map(x, SupplierDTO.class));
    }

    public void delete(String name) {
        supplierRepository.deleteByName(name);
    }

    public void post(SupplierDTO dto) {
        supplierRepository.save(new Supplier(dto.getName(),
        dto.getContactNumber(), dto.getSuppliedIngredients()));
    }

    public void patch(String name, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findByName(name);
        supplier.setName(dto.getName());
        supplier.setContactNumber(dto.getContactNumber());
        supplier.setSuppliedIngredients(dto.getSuppliedIngredients());
        supplierRepository.save(supplier);
    }
    
}
