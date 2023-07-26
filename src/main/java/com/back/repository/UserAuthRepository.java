package com.back.repository;

import com.back.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository <UserAuth, Long> {
    UserAuth findByUserIdAndAuthId(Long userId, Long authId);
}

