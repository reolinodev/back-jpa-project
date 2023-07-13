package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.UserDto;
import com.back.domain.sample.User;
import com.back.domain.sample.UserMapping;
import com.back.service.sample.UserService;
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
        User user  = userService.getUser(userId);
        System.out.println("result = " + user);

        //then
        Assertions.assertEquals("tester1", user.getUserNm());
    }

    @Test
    void createUser() {
        //given
        User user = new User();
        user.loginId = "tester18@gmail.com";
        user.userNm = "tester18";
        user.userPw = "a123123!!!!";
        user.telNo = "0100000013";
        user.useYn = "Y";

        //when
        User result  = userService.createUser(user);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("tester18", result.userNm);
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

    @Test
    @Transactional
    void getUsers() {
        //given
        UserDto params = new UserDto();
        params.size = 10;
        params.page = 2;

        //when
        Page<User> users = userService.getUsers(params);
        System.out.println("result = " + users);

        //then
        Assertions.assertEquals(2, users.getTotalPages());
    }

}
