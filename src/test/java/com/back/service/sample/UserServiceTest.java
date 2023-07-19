package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.dto.UserDto;
import com.back.domain.sample.params.UserParam;
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
        Long userId = 10L;

        //when
        UserDto user  = userService.getUser(userId);
        System.out.println("result = " + user);
        //then
        Assertions.assertEquals("test20", user.getUserNm());
    }

    @Test
    void createUser() {
        //given
        UserParam userParam = new UserParam();
        userParam.loginId = "tester18@gmail.com";
        userParam.userNm = "tester18";
        userParam.userPw = "a123123!!!!";
        userParam.telNo = "0100000013";
        userParam.useYn = "Y";

        //when
        User result  = userService.createUser(userParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("tester18", result.userNm);
    }

    @Test
    void updateUser() {
        //given
        UserParam userParam = new UserParam();
        userParam.userNm = "tester1@";
        userParam.useYn = "N";
        userParam.userPw = "a123123!!!";
        userParam.telNo = "01012345678";


        Long userId = 1L;

        //when
        User result  = userService.updateUser(userParam, userId);
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
        Assertions.assertEquals(2, users.getTotalPages());
    }
}
