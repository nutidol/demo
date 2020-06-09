package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SellerDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Seller;
import com.example.demo.entity.Store;
import com.example.demo.repository.SellerRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;

    @Override
    public List<SellerDto> getSeller(){
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerDto> sellerDtos;
        sellerDtos = sellers.stream().map(
                s -> new SellerDto(s.getId(), s.getSellerName(), s.getAge(), s.getStore().getStoreName())
        ).collect(Collectors.toList());
        return sellerDtos;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SellerDto createSeller(SellerDto sellerDto){
        Store store = new Store();
        store.setStoreName(sellerDto.getStoreName());
        storeRepository.save(store);

        Seller seller = new Seller();
        seller.setSellerName(sellerDto.getSellerName());
        seller.setAge(sellerDto.getAge());
        seller.setStore(store);
        sellerRepository.save(seller);
        return new SellerDto(seller.getId(), seller.getSellerName(), seller.getAge(), seller.getStore().getStoreName());
    }

    @Override
    public SellerDto updateSeller(int sellerId, SellerDto sellerDto){

        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
        if(sellerOptional.isPresent()){
            Seller seller = sellerOptional.get();
            seller.setSellerName(sellerDto.getSellerName());
            seller.setAge(sellerDto.getAge());
            seller.getStore().setStoreName(sellerDto.getStoreName());
            sellerRepository.save(seller);
            return new SellerDto(seller.getId(), seller.getSellerName(), seller.getAge(), seller.getStore().getStoreName());
        }
        return null;
    }

}