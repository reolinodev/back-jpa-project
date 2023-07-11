package com.back.repository.sample;

import com.back.domain.sample.User;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;

@Where(clause = "'Y'")
public interface UserRepository extends JpaRepository <User, Long> {

    User findByLoginIdAndUseYn(String loginId, String useYn);

    int countByLoginIdAndUserPw(String loginId, String userPw);
}

