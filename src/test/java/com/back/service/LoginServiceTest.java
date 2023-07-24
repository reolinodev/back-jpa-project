package com.back.service;

import com.back.domain.dto.LoginDto;
import com.back.domain.params.LoginParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void checkLoginId() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";

        //when
        int result  = loginService.checkLoginId(loginParam);
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void checkUserPw() throws Exception {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";
        loginParam.userPw = "1111";

        //when
        int result  = loginService.checkUserPw(loginParam);
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void getLoginUser() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";

        //when
        LoginDto result  = loginService.getLoginUser(loginParam.loginId);
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals("테스터2", result.userNm);
    }


}
