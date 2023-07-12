package com.back.repository.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.UserMapping;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Test
    void userCreateTest() {
        User user1 = new User();
        user1.userNm = "test1";
        user1.loginId="test1@gmail.com";
        user1.userPw ="123456789!";
        user1.telNo = "0100000001";

        User user2 = new User();
        user2.userNm = "test2";
        user2.loginId="test2@gmail.com";
        user2.userPw ="123456789!";
        user2.telNo = "0100000002";

        User user3 = new User();
        user3.userNm = "test3";
        user3.loginId="test3@gmail.com";
        user3.userPw ="123456789!";
        user3.telNo = "0100000003";

        User user4 = new User();
        user4.userNm = "test4";
        user4.loginId="test4@gmail.com";
        user4.userPw ="123456789!";
        user4.telNo = "0100000004";

        User user5 = new User();
        user5.userNm = "test5";
        user5.loginId="test5@gmail.com";
        user5.userPw ="123456789!";
        user5.telNo = "0100000005";

        User user6 = new User();
        user6.userNm = "test6";
        user6.loginId="test1@gmail.com";
        user6.userPw ="123456789!";
        user6.telNo = "0100000006";

        User user7 = new User();
        user7.userNm = "test7";
        user7.loginId="test7@gmail.com";
        user7.userPw ="123456789!";
        user7.telNo = "0100000007";

        User user8 = new User();
        user8.userNm = "test8";
        user8.loginId="test8@gmail.com";
        user8.userPw ="123456789!";
        user8.telNo = "0100000008";

        User user9 = new User();
        user9.userNm = "test9";
        user9.loginId="test9@gmail.com";
        user9.userPw ="123456789!";
        user9.telNo = "0100000009";

        User user10 = new User();
        user10.userNm = "test10";
        user10.loginId="test10@gmail.com";
        user10.userPw ="123456789!";
        user10.telNo = "0100000010";

        User user11 = new User();
        user11.userNm = "test11";
        user11.loginId="test11@gmail.com";
        user11.userPw ="123456789!";
        user11.telNo = "0100000011";

        User user12 = new User();
        user12.userNm = "test12";
        user12.loginId="test12@gmail.com";
        user12.userPw ="123456789!";
        user12.telNo = "0100000012";

       userRepository.saveAll(Lists.newArrayList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12));
       List<User> users = userRepository.findAll();
       users.forEach(System.out::println);
   }


    @Test
    void userPageTest() {

        Page<UserMapping> users = userRepository.findUsersBy(PageRequest.of(0,10, Sort.by(Order.desc("id"))));

        System.out.println("users : " + users);
        System.out.println("totalElement :" + users.getTotalElements());
        System.out.println("totalPages :" + users.getTotalPages());
        System.out.println("numberofElement : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());
        System.out.println("content : " + users.getContent());
    }


//
//    @Test
//    void userRelationTest2() {
//
//        List<UserHistory> results = userRepository.findById(1L).orElseThrow(RuntimeException::new).getUserHistories();
//        results.forEach(System.out::println);
//
//        System.out.println(result);
//
//        List<User> user = deptRepository
//            .findById(1L)
//            .orElseThrow(RuntimeException::new)
//            .getUser();
//
//        System.out.println(user);
//
//    }



}
