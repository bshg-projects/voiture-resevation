package com.example.voitureresevation.zsecurity.dao;

import com.example.voitureresevation.zsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}