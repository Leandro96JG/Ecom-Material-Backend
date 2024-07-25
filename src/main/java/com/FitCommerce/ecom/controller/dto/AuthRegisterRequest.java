package com.FitCommerce.ecom.controller.dto;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank(message = "Username is requerid") String username,
                                  @NotBlank(message = "Email is requerid") String email,
                                  @NotBlank(message = "Password is requerid") String password,
                                  @Valid AuthCreateRoleRequest roleRequest) {
}
