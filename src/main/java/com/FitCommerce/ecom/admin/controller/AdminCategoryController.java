package com.FitCommerce.ecom.admin.controller;

import com.FitCommerce.ecom.admin.dto.CategoryDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;
import com.FitCommerce.ecom.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
//Para generar los constructores de forma automatica a los campos final
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryService.createCategoryEntity(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryEntity);
    }
}
