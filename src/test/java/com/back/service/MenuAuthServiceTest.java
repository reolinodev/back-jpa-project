package com.back.service;

import com.back.domain.params.MenuAuthParam;
import com.back.domain.params.MenuAuthRow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuAuthServiceTest {

    @Autowired
    private MenuAuthService menuAuthService;

    @Test
    void saveMenuAuth() {
        //given
        MenuAuthParam menuAuthParam = new MenuAuthParam();
        menuAuthParam.menuId = 2L;

        MenuAuthRow[] updatedRows = new MenuAuthRow[3];

        MenuAuthRow updateMenuAuth1 = new MenuAuthRow();
        updateMenuAuth1.authId = 1L;
        updateMenuAuth1.useYn = "N";

        MenuAuthRow updateMenuAuth2 = new MenuAuthRow();
        updateMenuAuth2.authId = 2L;
        updateMenuAuth2.useYn = "N";

        MenuAuthRow updateMenuAuth3 = new MenuAuthRow();
        updateMenuAuth3.authId = 3L;
        updateMenuAuth3.useYn = "N";

        updatedRows[0] = updateMenuAuth1;
        updatedRows[1] = updateMenuAuth2;
        updatedRows[2] = updateMenuAuth3;

        menuAuthParam.updatedRows = updatedRows;
        menuAuthParam.updatedId = 1L;

        //when
        menuAuthService.saveMenuAuth(menuAuthParam);
    }


    @Test
    void getMenuAuths() {
        //given
        Long menuId = 4L;

        //when
        var result = menuAuthService.getMenuAuths(menuId);

        System.out.println("<<"+result);

    }
}
