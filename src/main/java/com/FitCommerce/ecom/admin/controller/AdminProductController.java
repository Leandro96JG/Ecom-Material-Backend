package com.FitCommerce.ecom.admin.controller;

import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class AdminProductController {
    private final AdminProductService adminProductService;

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('ADMIN')")
    //@ModelAttribute se usa para recibir los datos directamente desde el form
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = this.adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }
}
