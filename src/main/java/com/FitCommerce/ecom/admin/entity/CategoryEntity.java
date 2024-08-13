package com.FitCommerce.ecom.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    //Indica que es para guardar grandes cantidades de datos
    @Lob
    private String description;
}
