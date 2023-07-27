package com.back.service;

import com.back.domain.Menu;
import com.back.domain.dto.MenuDto;
import com.back.domain.dto.MenuTreeIF;
import com.back.domain.params.MenuParam;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void createMenu() {
        //given
        MenuParam menuParam = new MenuParam();
        menuParam.menuNm ="메뉴별권한정보";
        menuParam.menuLv = 2;
        menuParam.prnMenuId = 2L;
        menuParam.authRole = "WEB";
        menuParam.url = "/page/user/menuAuth";
        menuParam.ord = 2;
        menuParam.useYn = "Y";
        menuParam.menuType = "DOMAIN";

        //when
        Menu result  = menuService.createMenu(menuParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("사용자관리", result.menuNm);
    }

    @Test
    void getMenu() {
        //given
        Long id = 4L;

        //when
        MenuDto result  = menuService.getMenu(id);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("기타관리", result.menuNm);
    }

    @Test
    void checkChildMenu() {
        //given
        Long id = 2L;

        //when
        int result  = menuService.checkChildMenu(id);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(2, result);
    }

    @Test
    void deleteMenu() {
        //given
        Long id = 13L;

        //when
        menuService.deleteMenu(id);
    }

    @Test
    void getMenuTree() {
        //given
        String authRole = "WEB";

        //when
        List<MenuTreeIF> result  = menuService.getMenuTree(authRole);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(12, result.size());
    }

}
