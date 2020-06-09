package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SellerDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final SellerService sellerService;


    @GetMapping("/product")
    public List<ProductDto> getProduct(){
        return productService.getProduct();
    }
    @GetMapping("/seller")
    public List<SellerDto> getSeller(){
        return sellerService.getSeller();
    }


    @PostMapping("/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @PostMapping("/seller")
    public SellerDto createSeller(@RequestBody SellerDto sellerDto){
        return sellerService.createSeller(sellerDto);
    }

    @PutMapping("/seller/{id}")
    public SellerDto updateSeller(@PathVariable int id, @RequestBody SellerDto sellerDto){
        return sellerService.updateSeller(id, sellerDto);
    }

    @DeleteMapping("/product/{id}")
    public String delete (@PathVariable String id) {
        int productId = Integer.parseInt(id);
        productService.deleteProduct(productId);
        return "productID: " +productId+" is deleted!";
    }

}
