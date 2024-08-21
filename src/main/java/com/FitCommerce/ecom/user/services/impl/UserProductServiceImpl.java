package com.FitCommerce.ecom.user.services.impl;

import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.admin.entity.ProductEntity;
import com.FitCommerce.ecom.admin.mappers.ProductMapper;
import com.FitCommerce.ecom.admin.repository.ProductRepository;
import com.FitCommerce.ecom.user.services.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService{

    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts(){
        List<ProductEntity> productEntities = this.productRepository.findAll();
        return productEntities.stream().map(ProductMapper.INSTANCE::toDto).toList();
    }

    public List<ProductDto> getAllProductsByName(String name){
        List<ProductEntity> productEntities = this.productRepository.findAllByNameContaining(name);
        return productEntities.stream().map(ProductMapper.INSTANCE::toDto).toList();
    }
}
