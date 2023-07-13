package com.back.repository.sample;

import com.back.domain.sample.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByLoginIdAndUseYn(String loginId, String useYn);

    int countByLoginIdAndUserPw(String loginId, String userPw);

    Page<User> findUsersBy(Pageable pageable);

    User findByLoginId(String loginId);
}

