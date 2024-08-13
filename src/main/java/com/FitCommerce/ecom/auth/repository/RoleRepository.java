package com.FitCommerce.ecom.auth.repository;

import com.FitCommerce.ecom.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
//    List<RoleEntity> findRoleEntityListByUsername(String username);
}
