package com.FitCommerce.ecom.admin.service;

import com.FitCommerce.ecom.admin.dto.CategoryDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity createCategoryEntity(CategoryDto categoryDto);
}
