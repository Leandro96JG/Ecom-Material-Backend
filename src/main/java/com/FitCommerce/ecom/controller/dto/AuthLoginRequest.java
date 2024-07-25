package com.FitCommerce.ecom.controller.dto;

public record AuthLoginRequest(@NotBlanck String username,
                               @NotBlanck String password) {
}
