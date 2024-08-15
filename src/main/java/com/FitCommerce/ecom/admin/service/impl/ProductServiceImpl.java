package com.FitCommerce.ecom.admin.service.impl;

import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;
import com.FitCommerce.ecom.admin.entity.ProductEntity;
import com.FitCommerce.ecom.admin.mappers.ProductMapper;
import com.FitCommerce.ecom.admin.repository.CategoryRepository;
import com.FitCommerce.ecom.admin.repository.ProductRepository;
import com.FitCommerce.ecom.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {

        byte[] imageBytes = productDto.getImg().getBytes();

        ProductEntity product = ProductMapper.INSTANCE.toEntity(productDto);
        product.setImg(imageBytes);

        CategoryEntity category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()-> new NoSuchElementException("Categoria no encontrada"));

        product.setCategory(category);
        return ProductMapper.INSTANCE.toDto(productRepository.save(product));

    }

    public List<ProductDto> getAllProducts(){
        List<ProductEntity> productEntities = this.productRepository.findAll();
        return productEntities.stream().map(ProductMapper.INSTANCE::toDto).toList();
    }

    public List<ProductDto> getAllProductsByName(String name){
        List<ProductEntity> productEntities = this.productRepository.findAllByNameContaining(name);
        return productEntities.stream().map(ProductMapper.INSTANCE::toDto).toList();
    }
    public boolean deleteProduct(Long id){
        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
