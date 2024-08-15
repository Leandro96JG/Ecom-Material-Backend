package com.FitCommerce.ecom.admin.repository;

import com.FitCommerce.ecom.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    //Containing: busca cualquier coincidencia parcial del texto
    List<ProductEntity> findAllByNameContaining(String title);
}
