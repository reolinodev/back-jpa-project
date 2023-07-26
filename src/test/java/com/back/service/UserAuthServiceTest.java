package com.back.service;

import com.back.domain.UserAuth;
import com.back.domain.dto.UserAuthDto;
import com.back.domain.dto.UserAuthInputDto;
import com.back.domain.dto.UserDto;
import com.back.domain.params.UserAuthParam;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class UserAuthServiceTest {

    @Autowired
    private UserAuthService userAuthService;


    @Test
    void createUserAuths() {
        //given
        UserAuthParam userAuthParam = new UserAuthParam();
        userAuthParam.authId = 1L;
        userAuthParam.userArr = new Long[]{2L, 6L, 9L};
        userAuthParam.createdId = 2L;

        //when
        List<UserAuth> result  = userAuthService.createUserAuths(userAuthParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(4, result.size());
    }


    @Test
    void deleteUserAuth() {
        //given
        UserAuthParam userAuthParam = new UserAuthParam();
        userAuthParam.authId = 1L;
        userAuthParam.userArr = new Long[]{2L, 6L, 9L};
        userAuthParam.updatedId = 2L;

        //when
        List<UserAuth> result  = userAuthService.deleteUserAuth(userAuthParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void getUserAuths() {
        //given
        UserAuthParam userAuthParam = new UserAuthParam();
        userAuthParam.authRole = "WEB";
        userAuthParam.authId = 1L;
        userAuthParam.userNm = "테스터8";
        userAuthParam.loginId = "test";

        //when
        Page<UserAuthDto> result  = userAuthService.getUserAuths(userAuthParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result.getTotalElements());
    }


    @Test
    void getInputUserAuths() {
        //given
        UserAuthParam userAuthParam = new UserAuthParam();
        userAuthParam.authId = 2L;

        //when
        Page<UserAuthInputDto> result  = userAuthService.getInputUserAuths(userAuthParam);
        System.out.println("result = " + result);

        //then
//        Assertions.assertEquals(1, result.getTotalElements());
    }
}
