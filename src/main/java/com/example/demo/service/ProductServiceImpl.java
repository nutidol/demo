package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<ProductDto> getProduct(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(
                p -> new ProductDto(p.getId(),p.getProductName(),p.getPrice(),p.getCustomer().getCustomerName(),p.getStore().getStoreName())
        ).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public void deleteProduct(int id) {

        productRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductDto createProduct(ProductDto productDto){
        Store store = new Store();
        store.setStoreName(productDto.getStoreName());
        storeRepository.save(store);

        Customer customer = new Customer();
        customer.setCustomerName(productDto.getCustomerName());
        customerRepository.save(customer);

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setStore(store);
        product.setCustomer(customer);
        productRepository.save(product);
        return new ProductDto(product.getId(), product.getProductName(), product.getPrice(), product.getCustomer().getCustomerName(), product.getStore().getStoreName());
    }

}
