package com.back.repository.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.UserHistory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeptRepository deptRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Test
    void userRelationTest() {

//        List<User> result = userRepository.findAllByDeptId(1L);
//        System.out.println(result);
    }

    @Test
    void userRelationTest2() {

        List<UserHistory> results = userRepository.findById(1L).orElseThrow(RuntimeException::new).getUserHistories();
        results.forEach(System.out::println);
//
//        System.out.println(result);

//        List<User> user = deptRepository
//            .findById(1L)
//            .orElseThrow(RuntimeException::new)
//            .getUser();

//        System.out.println(user);

    }



}
