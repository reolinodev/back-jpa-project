package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.dto.UserDto;
import com.back.domain.sample.params.UserParam;
import java.util.List;
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
        Assertions.assertEquals("tester1", user.getUserNm());
    }

    @Test
    void createUser() {
        //given
        UserParam UserParam = new UserParam();
        UserParam.loginId = "tester18@gmail.com";
        UserParam.userNm = "tester18";
        UserParam.userPw = "a123123!!!!";
        UserParam.telNo = "0100000013";
        UserParam.useYn = "Y";

        //when
        User result  = userService.createUser(UserParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("tester18", result.userNm);
    }

    @Test
    void updateUser() {
        //given
        UserParam UserParam = new UserParam();
        UserParam.userNm = "tester1@";
        UserParam.useYn = "N";
        UserParam.userPw = "a123123!!!";
        UserParam.telNo = "01012345678";

        Long userId = 1L;

        //when
        User result  = userService.updateUser(UserParam, userId);
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
        UserParam UserParam = new UserParam();
        UserParam.size = 10;
        UserParam.page = 2;

        //when
        Page<UserDto> users = userService.getUsers(UserParam);
        System.out.println("result = " + users);

        //then
        Assertions.assertEquals(2, users.getTotalPages());
    }
}
