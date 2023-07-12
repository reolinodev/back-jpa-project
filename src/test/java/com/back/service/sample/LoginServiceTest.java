package com.back.service.sample;

import com.back.domain.sample.LoginDto;
import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.User;
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
        LoginDto params = new LoginDto();
        params.loginId = "test2@gmail.com";

        //when
        User result = loginService.checkExistUser(params);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(2, result.id);
    }

    @Test
    void checkUserPw() {
        //given
        LoginDto params = new LoginDto();
        params.loginId = "test2@gmail.com";
        params.userPw = "a123123!";

        //when
        int result = loginService.checkUserPw(params);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void createLoginHistory() {
        //given
        LoginHistory params = new LoginHistory();
        LoginDto params2 = new LoginDto();
        params2.loginId = "test2@gmail.com";
        params2.userPw = "a123123!";

        params.user = loginService.checkExistUser(params2);
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
        LoginDto params = new LoginDto();
        params.loginId = "test2@gmail.com";
        params.userPw = "a123123!";

        User loginData = loginService.checkExistUser(params);
        int loginCnt = loginService.checkUserPw(params);

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
        LoginDto params = new LoginDto();
        params.loginId = "test2@gmail.com";
        params.userPw= "a123123!!";
        params.useYn = "Y";

        User loginData = loginService.checkExistUser(params);

        //when
        User result = loginService.updateUserPw(params, loginData);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("a123123!!", result.userPw);
    }


}
