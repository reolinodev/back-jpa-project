package com.back.repository;

import com.back.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository <User, Long> {

    User findByUserNm(String userNm);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    List<User> findFirst2ByUserNm(String userNm);

    List<User> findTop2ByUserNm(String userNm);

    List<User> findLast1ByUserNm(String userNm);

    List<User> findByEmailAndUserNm(String email, String userNm);

    List<User> findByEmailOrUserNm(String email, String userNm);

    List<User> findByCreatedAtAfter(LocalDateTime yesterday);

    List<User> findByUserIdAfter(Long id);

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<User> findByUserIdBetween(Long id, Long id2);

    List<User> findByUserIdGreaterThanEqualAndUserIdLessThanEqual(Long id, Long id2);

    List<User> findByUserIdIsNotNull();

    List<User> findByUserNmIn(List<String> names);

}

