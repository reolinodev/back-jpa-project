package com.back.repository;

import com.back.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository <Auth, Long> {
    int countByAuthVal(String authVal);
}

