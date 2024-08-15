package com.FitCommerce.ecom.admin.controller;

import com.FitCommerce.ecom.admin.dto.CategoryDto;
import com.FitCommerce.ecom.admin.entity.CategoryEntity;
import com.FitCommerce.ecom.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
//Para generar los constructores de forma automatica a los campos final
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class AdminCategoryController {

    private final CategoryService categoryService;


    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryService.createCategoryEntity(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryEntity);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> hello(){
        return ResponseEntity.status(HttpStatus.OK).body("Hola desde el back");
    }

    @GetMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategoryEntity>> allCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.categoryService.getAllCategories()
        );
    }

}
