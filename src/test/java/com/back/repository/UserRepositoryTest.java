package com.back.repository;

import com.back.domain.User;
import com.back.repository.UserRepository;
import java.util.List;
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

//    @Autowired
//    DeptRepository deptRepository;
//
//    @Autowired
//    UserHistoryRepository userHistoryRepository;

//    @Test
//    void userCreateTest() {
//        User user1 = new User();
//        user1.userNm = "test221";
//        user1.loginId="test221@gmail.com";
//        user1.userPw ="123456789!";
//        user1.telNo = "0100000001";
//
//
//
//       userRepository.save(user1);
//       List<User> users = userRepository.findAll();
//       users.forEach(System.out::println);
//   }
//
//    @Test
//    void findById() {
//
//        User users = userRepository.findById(1L).orElseThrow(RuntimeException::new);
//
//        System.out.println("users : " + users);
//    }
//
//
//
//    @Test
//    void userPageTest() {
//
//        Page<User> users = userRepository.findUsersBy(PageRequest.of(1,10, Sort.by(Order.desc("id"))));
//
//        System.out.println("users : " + users);
//        System.out.println("totalElement :" + users.getTotalElements());
//        System.out.println("totalPages :" + users.getTotalPages());
//        System.out.println("numberofElement : " + users.getNumberOfElements());
//        System.out.println("sort : " + users.getSort());
//        System.out.println("size : " + users.getSize());
//        System.out.println("content : " + users.getContent());
//
//
//    }
//
//    @Test
//    void userSaveTest() {
//
//        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
//        user.userPw = "34343";
//        System.out.println("users : " + user);
//
//        user.dept = deptRepository.findById(3L).orElseThrow(RuntimeException::new);
//
//        userRepository.save(user);
//    }

}
