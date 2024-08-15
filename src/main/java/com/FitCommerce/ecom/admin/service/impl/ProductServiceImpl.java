package com.FitCommerce.ecom.admin.service.impl;

import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;
import com.FitCommerce.ecom.admin.entity.ProductEntity;
import com.FitCommerce.ecom.admin.repository.CategoryRepository;
import com.FitCommerce.ecom.admin.repository.ProductRepository;
import com.FitCommerce.ecom.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        CategoryEntity category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAllProducts(){
        List<ProductEntity> productEntities = this.productRepository.findAll();
        return productEntities.stream().map(ProductEntity::getDto).toList();
    }

}
