package com.back.repository;

import com.back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByLoginIdAndUseYn(String loginId, String useYn);

    int countByLoginIdAndUserPw(String loginId, String userPw);

    int countByLoginIdAndUseYn(String loginId, String useYn);
}

