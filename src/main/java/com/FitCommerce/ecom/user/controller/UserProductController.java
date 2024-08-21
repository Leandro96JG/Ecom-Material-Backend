package com.FitCommerce.ecom.user.controller;


import com.FitCommerce.ecom.admin.dto.ProductDto;
import com.FitCommerce.ecom.user.services.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserProductController {

    private final UserProductService userProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = this.userProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Tonotos";
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> productsDto = this.userProductService.getAllProductsByName(name);
        return ResponseEntity.ok(productsDto);
    }

}
