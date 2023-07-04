package com.back.service;

import com.back.domain.sample.User;
import com.back.service.sample.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;



    @Test
    void getUserData() {
        //given
        Long userId = 4L;

        //when
        User user  = userService.getUserData(userId);
        System.out.println("result = " + user);

        //then
        Assertions.assertEquals("jack6", user.userNm);
    }



    @Test
    void createUser() {
        //given
        User user = new User();
        user.userNm = "tester2";
        user.birth = "19830201";
        user.email = "tester2@gmail.com";
        user.userPw = "2222";
        user.telNo = "01012345678";

        //when
        User result  = userService.createUser(user);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("tester2", result.userNm);
    }

    @Test
    void updateUser() {
        //given
        User user = new User();
        user.userNm = "tester3";
        user.birth = "19830301";
        user.email = "tester3@gmail.com";
        user.userPw = "1111";
        user.telNo = "01012345678";

        Long userId = 21L;

        //when
        User result  = userService.updateUser(user, userId);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("tester3", result.userNm);
    }


    @Test
    void deleteUser() {
        //given
        Long userId = 25L;

        //when
        Boolean result = userService.deleteUser(userId);

        //then
        Assertions.assertEquals(false, result);
    }

}
