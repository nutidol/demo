package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SellerDto;

import java.util.List;

public interface SellerService {
    List<SellerDto> getSeller();
    SellerDto createSeller(SellerDto sellerDto);
    SellerDto updateSeller(int sellerId, SellerDto sellerDto);
}
