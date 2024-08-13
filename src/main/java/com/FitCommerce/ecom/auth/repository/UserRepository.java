package com.FitCommerce.ecom.auth.repository;


import com.FitCommerce.ecom.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//Jpa tiene más funcionalidades que crud repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);
}
