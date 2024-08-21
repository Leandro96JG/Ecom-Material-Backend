package com.FitCommerce.ecom.user.services;

import com.FitCommerce.ecom.admin.dto.ProductDto;

import java.util.List;

public interface UserProductService {
    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductsByName(String name);

}
