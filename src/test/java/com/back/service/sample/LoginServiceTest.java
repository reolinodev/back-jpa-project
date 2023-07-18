package com.back.service.sample;

import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.User;
import com.back.domain.sample.params.LoginParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void checkExistUser() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";

        //when
        User result = loginService.checkUser(loginParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(2, result.id);
    }

    @Test
    void checkUserPw() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";
        loginParam.userPw = "a123123!!";

        //when
        int result = loginService.checkUserPw(loginParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void createLoginHistory() {
        //given
        LoginHistory params = new LoginHistory();
        LoginParam params2 = new LoginParam();
        params2.loginId = "test2@gmail.com";
        params2.userPw = "a123123!!";

        params.user = loginService.checkUser(params2);
        params.device = "WEB";

        //when
        LoginHistory result = loginService.createLoginHistory(params);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("WEB", result.device);
    }


    @Test
    void updateLoginFailCnt() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";
        loginParam.userPw = "a123123!";

        User loginData = loginService.checkUser(loginParam);
        int loginCnt = loginService.checkUserPw(loginParam);

        boolean chkLogin = loginCnt != 0;

        //when
        User result = loginService.updateLoginFailCnt(loginData, chkLogin);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(0, result.loginFailCnt);
    }

    @Test
    void updateUserPw() {
        //given
        LoginParam loginParam = new LoginParam();
        loginParam.loginId = "test2@gmail.com";
        loginParam.userPw= "a123123!!!!!";

        //when
        User result = loginService.updateUserPw(loginParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("a123123!!!!!", result.userPw);
    }


}
