package com.back.service;

import com.back.domain.Auth;
import com.back.domain.dto.AuthDto;
import com.back.domain.params.AuthParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    void createAuth() {
        //given
        AuthParam authParam = new AuthParam();
        authParam.authNm ="시스템개발자";
        authParam.authVal ="SYSTEM-ENGINEER";
        authParam.authRole = "MAIN-WEB";
        authParam.ord = "1";
        authParam.memo = "시스템 개발 및 유지보수";

        //when
        Auth result  = authService.createAuth(authParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("시스템개발자", result.authNm);
    }


    @Test
    void checkAuthVal() {
        //given
        AuthParam authParam = new AuthParam();
        authParam.authVal ="SYSTEM-ENGINEER";

        //when
        int result  = authService.checkAuthVal(authParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    void getAuths() {
        //given
        AuthParam authParam = new AuthParam();
        authParam.size = 10;
        authParam.page = 1;
//        authParam.useYn = "Y";
//        authParam.authRole = "WEB";
//        authParam.authNm ="시스템";
//        authParam.authVal = "SYSTEM";

        //when
        Page<AuthDto> auths = authService.getAuths(authParam);
        System.out.println("result = " + auths);

        //then
        Assertions.assertEquals(1, auths.getTotalPages());
    }


    @Test
    void getAuth() {
        //given
        Long authId =2L;

        //when
        AuthDto getAuthResult = authService.getAuth(authId);
        System.out.println("result = " + getAuthResult);

        //then
        Assertions.assertEquals("시스템 관리자", getAuthResult.authNm);
    }


    @Test
    void updateAuth() {
        //given
        Long authId =2L;
        AuthParam authParam = new AuthParam();
        authParam.updatedId = 2L;
        authParam.useYn = "N";
        authParam.ord = "3";
        authParam.memo = "사이트를 관리하는 관리자1";

        //when
        Auth updateAuthResult = authService.updateAuth(authId, authParam);
        System.out.println("result = " + updateAuthResult);

        //then
        Assertions.assertEquals("N", updateAuthResult.useYn);
    }

}
