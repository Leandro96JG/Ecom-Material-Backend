package com.FitCommerce.ecom.admin.controller;

import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.admin.entity.ProductEntity;
import com.FitCommerce.ecom.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {
    private final AdminProductService adminProductService;

    @PostMapping("/add-product")
    //@ModelAttribute se usa para recibir los datos directamente desde el form
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = this.adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> productsDto = this.adminProductService.getAllProductsByName(name);
        return ResponseEntity.ok(productsDto);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable  Long id){
       boolean deleted = this.adminProductService.deleteProduct(id);
       if(deleted){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }

}
