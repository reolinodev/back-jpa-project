package com.back.service;

import com.back.domain.User;
import com.back.domain.dto.UserDto;
import com.back.domain.params.UserParam;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void getUser() {
        //given
        Long userId = 1L;

        //when
        UserDto user  = userService.getUser(userId);
        System.out.println("result = " + user);
        //then
        Assertions.assertEquals("관리자", user.getUserNm());
    }

    @Test
    void createUser() throws Exception {
        //given
        UserParam userParam = new UserParam();
        userParam.loginId = "test1@gmail.com";
        userParam.userNm = "테스터1";
        userParam.userPw = "1111";
        userParam.telNo = "010222244444";

        //when
        User result  = userService.createUser(userParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("테스터1", result.userNm);
    }

    @Test
    void updateUser() throws Exception {
        //given
        UserParam userParam = new UserParam();

        userParam.useYn = "Y";
        userParam.telNo = "01012345678";
        userParam.userPw = "1234";

        Long userId = 3L;

        //when
        User result  = userService.updateUser(userParam, userId);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("N", result.useYn);
    }


    @Test
    @Transactional
    void getUsers() {
        //given
        UserParam userParam = new UserParam();
        userParam.size = 10;
        userParam.page = 1;

        //when
        Page<UserDto> users = userService.getUsers(userParam);
        System.out.println("result = " + users);

        //then
        Assertions.assertEquals(1, users.getTotalPages());
    }

    @Test
    void checkLoginId() {
        //given
        String loginId = "admin@gmail.com";

        //when
        int result  = userService.checkLoginId(loginId);
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals(0, result);
    }
}
