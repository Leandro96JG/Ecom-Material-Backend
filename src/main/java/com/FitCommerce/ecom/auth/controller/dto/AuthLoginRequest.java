package com.FitCommerce.ecom.auth.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank(message = "Username is requerid") String username,
                               @NotBlank(message = "Password is requerid") String password) {
}
