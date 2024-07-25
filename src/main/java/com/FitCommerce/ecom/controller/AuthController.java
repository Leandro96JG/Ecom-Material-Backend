package com.FitCommerce.ecom.controller;

import com.FitCommerce.ecom.controller.dto.AuthLoginRequest;
import com.FitCommerce.ecom.controller.dto.AuthResponse;
import com.FitCommerce.ecom.service.UserDetailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserDetailServiceImpl userDetailService;

    public AuthController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

//    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest userRequest){
//        return new ResponseEntity<>(this.userDetailService.)
//    }
}
