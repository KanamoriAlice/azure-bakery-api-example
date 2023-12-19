package com.azuresdk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.azuresdk.dtos.ProductDTO;
import com.azuresdk.model.Product;
import com.azuresdk.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    public ProductDTO get(String name) {
        return modelMapper.map(productRepository.findByName(name), ProductDTO.class);
    }

    public Page<ProductDTO> getByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(x -> modelMapper.map(x, ProductDTO.class));
    }

    public void delete(String name) {
        productRepository.deleteByName(name);
    }

    public void post(ProductDTO dto) {
        productRepository.save(new Product(dto.getName(), dto.getPrice(),
            dto.getCategory(), dto.getIngredients(), dto.isAvailable()));
    }

    public void patch(String name, ProductDTO dto) {
        Product product = productRepository.findByName(name);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setIngredients(dto.getIngredients());
        product.setAvailable(dto.isAvailable());
        productRepository.save(product);
    }
    
}
