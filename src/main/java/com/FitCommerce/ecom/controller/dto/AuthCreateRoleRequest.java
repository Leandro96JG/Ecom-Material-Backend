package com.FitCommerce.ecom.controller.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public record AuthCreateRoleRequest(
        @Size(max = 2,message = "The user cannot have more than 2 roles")List<String> roleListName
        ) {
}
