package com.FitCommerce.ecom.admin.service;

import com.FitCommerce.ecom.admin.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {
    ProductDto addProduct(ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductsByName(String name);

    List<ProductDto> getAllProducts();

    boolean deleteProduct(Long id);
}
