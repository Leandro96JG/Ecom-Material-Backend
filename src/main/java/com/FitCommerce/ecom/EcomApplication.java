package com.FitCommerce.ecom;

import com.FitCommerce.ecom.entity.PermissionEntity;
import com.FitCommerce.ecom.entity.RoleEntity;
import com.FitCommerce.ecom.entity.enums.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class EcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

}
