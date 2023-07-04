package com.back.repository.sample;

import com.back.domain.sample.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByUserId(Long userId);

}

