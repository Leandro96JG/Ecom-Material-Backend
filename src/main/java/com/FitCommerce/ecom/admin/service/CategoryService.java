package com.FitCommerce.ecom.admin.service;

import com.FitCommerce.ecom.admin.dto.CategoryDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity createCategoryEntity(CategoryDto categoryDto);
    List<CategoryEntity> getAllCategories();
}
