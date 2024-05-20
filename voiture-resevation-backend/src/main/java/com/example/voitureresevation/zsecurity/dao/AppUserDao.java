package com.example.voitureresevation.zsecurity.dao;

import com.example.voitureresevation.zsecurity.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserDao extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    int deleteByUsername(String username);

    AppUser findByEmail(String email);
}
