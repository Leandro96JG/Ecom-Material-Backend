package com.FitCommerce.ecom.controller;

import com.FitCommerce.ecom.controller.dto.AuthLoginRequest;
import com.FitCommerce.ecom.controller.dto.AuthRegisterRequest;
import com.FitCommerce.ecom.controller.dto.AuthResponse;
import com.FitCommerce.ecom.service.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//para no usar contructores, lo mismo que un @Autowrite
//@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class AuthController {
    private final UserDetailServiceImpl userDetailService;

    public AuthController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "Tonotos";
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterRequest userRequest){
        System.out.println("Nombre del Rol: "+userRequest.roleRequest());
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest),HttpStatus.CREATED);
    }
}
