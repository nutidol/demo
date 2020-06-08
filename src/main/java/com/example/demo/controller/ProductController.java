package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    @Autowired //get this bean to handle data
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getProduct")
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @PostMapping(path = "/add") //map post request
    //should create this as a method in service class??
    public @ResponseBody String addNewProduct (@RequestParam String productName, @RequestParam int price){
        Product p = new Product();
        p.setProductName(productName);
        p.setPrice(price);
        productRepository.save(p);
        return "Added!";
    }

    @DeleteMapping("/deleteProduct/{id}")
    public @ResponseBody String delete (@PathVariable String id) {
        int productId = Integer.parseInt(id);
        productService.deleteProduct(productId);
        return "productID: " +productId+" is deleted!";
    }

}
