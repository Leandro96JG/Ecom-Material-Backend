package com.FitCommerce.ecom.admin.service.impl;

import com.FitCommerce.ecom.admin.dto.CategoryDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;
import com.FitCommerce.ecom.admin.repository.CategoryRepository;
import com.FitCommerce.ecom.admin.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity createCategoryEntity(CategoryDto categoryDto){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.name());
        categoryEntity.setDescription(categoryDto.description());
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }

}
