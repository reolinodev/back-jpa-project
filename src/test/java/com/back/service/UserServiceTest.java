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
        Long userId = 1L;

        //when
        User user  = userService.getUserData(userId);
        System.out.println("result = " + user);

        //then
        Assertions.assertEquals("test1", user.userNm);
    }

    @Test
    void createUser() {
        //given
        User user = new User();
        user.loginId = "tester13@gmail.com";
        user.userNm = "tester13";
        user.userPw = "2222222222";
        user.telNo = "0100000013";
        user.useYn = "Y";

        //when
        User result  = userService.createUser(user);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("tester13", result.userNm);
    }

    @Test
    void updateUser() {
        //given
        User user = new User();
        user.userNm = "tester1@";
        user.useYn = "N";
        user.userPw = "a123123!!!";
        user.telNo = "01012345678";

        Long userId = 1L;

        //when
        User result  = userService.updateUser(user, userId);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("N", result.useYn);
    }


    @Test
    void deleteUser() {
        //given
        Long userId = 14L;

        //when
        Boolean result = userService.deleteUser(userId);

        //then
        Assertions.assertEquals(false, result);
    }

}
