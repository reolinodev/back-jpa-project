package com.back.repository.sample;

import com.back.domain.sample.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SampleRepository extends JpaRepository <User, Long> {



//    User findByUserNm(String userNm);
//
//    User findByEmail(String email);
//
//    User getByEmail(String email);
//
//    User readByEmail(String email);
//
//    User queryByEmail(String email);
//
//    User searchByEmail(String email);
//
//    User streamByEmail(String email);
//
//    User findUserByEmail(String email);
//
//    List<User> findFirst2ByUserNm(String userNm);
//
//    List<User> findTop2ByUserNm(String userNm);
//
//    List<User> findByEmailAndUserNm(String email, String userNm);
//
//    List<User> findByEmailOrUserNm(String email, String userNm);
//
//    List<User> findByCreatedAtAfter(LocalDateTime yesterday);
//
//    List<User> findByIdAfter(Long id);
//
//    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
//
//    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);
//
//    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
//
//    List<User> findByIdBetween(Long id, Long id2);
//
//    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id, Long id2);
//
//    List<User> findByIdIsNotNull();
//
//    List<User> findByUserNmIn(List<String> names);
//
//    List<User> findTop1ByUserNm(String userNm);
//
//    List<User> findLast1ByUserNm(String userNm);
//
//    List<User> findTopByUserNmOrderByIdDesc(String userNm);
//
//    List<User> findFirstByUserNmOrderByIdDescEmailAsc(String userNm);
//
//    List<User> findFirstByUserNm(String userNm, Sort sort);
//
//    Page<User> findByEmail(String userNm, Pageable pageable);
//
    @Query(value="select * from sample.tb_user "
        + "limit 1;", nativeQuery = true )
    Map<String, Object> findRowRecord();

    @Query(value="select a from User a "
        + "where userNm = :userNm and telNo = :telNo order by userNm desc")
    List<User> findUserCustom(
        @Param("userNm") String userNm,
        @Param("telNo") String telNo);


    @Modifying
    @Transactional
    @Query(value="update sample.tb_user set updated_at = now() ", nativeQuery = true )
    int updateUserCreated();
}

