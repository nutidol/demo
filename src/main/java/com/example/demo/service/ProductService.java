package com.example.demo.service;

import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductService{
    List<ProductDto> getProduct();
    void deleteProduct(int id);
    ProductDto createProduct(ProductDto productDto);
}
