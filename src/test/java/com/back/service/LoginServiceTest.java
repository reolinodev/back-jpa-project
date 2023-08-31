package com.back.service;

import com.back.domain.LoginHistory;
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
        loginParam.loginId = "admin@gmail.com";
        loginParam.authRole = "WEB";

        //when
        LoginDto result  = loginService.getLoginUser(loginParam.loginId, loginParam.authRole);
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals("테스터2", result.userNm);
    }


    @Test
    void getTokenInfo() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MkBnbWFpbC5jb20iLCJ1c2VyTm0iOiLthYzsiqTthLAyIiwibG9naW5JZCI6InRlc3QyQGdtYWlsLmNvbSIsInVzZXJQdyI6IjBmZmUxYWJkMWEwODIxNTM1M2MyMzNkNmUwMDk2MTNlOTVlZWM0MjUzODMyYTc2MWFmMjhmZjM3YWM1YTE1MGMiLCJleHAiOjIwMDU2MDY3NzEsImlhdCI6MTY5MDI0Njc3MSwidXNlcklkIjo0fQ.pTgXa3qc6hFsWGHzKdJpeLaNQSf9sdaZD-b9PQhFfV8";

        //when
        LoginHistory result  = loginService.getTokenInfo(loginParam);
        System.out.println("result = " + result);
        //then
        Assertions.assertEquals(9, result.id);
    }


}
