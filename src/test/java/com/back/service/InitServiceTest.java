package com.back.service;

import com.back.domain.Auth;
import com.back.domain.Code;
import com.back.domain.CodeGrp;
import com.back.domain.Menu;
import com.back.domain.MenuAuth;
import com.back.domain.User;
import com.back.domain.UserAuth;
import com.back.domain.params.UserParam;
import com.back.repository.AuthRepository;
import com.back.repository.CodeGrpRepository;
import com.back.repository.CodeRepository;
import com.back.repository.MenuAuthRepository;
import com.back.repository.MenuRepository;
import com.back.repository.UserAuthRepository;
import com.back.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InitServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private CodeGrpRepository codeGrpRepository;

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuAuthRepository menuAuthRepository;

    @Test
    void createUser() throws Exception {
        UserParam userParam = new UserParam();
        userParam.loginId = "admin@gmail.com";
        userParam.userNm = "관리자";
        userParam.userPw = "1234";
        userParam.telNo = "01011112222";
        userParam.createdId = 1L;

        User user = userService.createUser(userParam);
        UserParam userParam2 = new UserParam();
        userParam2.pwInitYn = "Y";

        userService.updateUser(userParam2, user.id);
    }

    @Test
    void createAuth()  {
        Auth auth = new Auth();
        auth.authNm = "시스템관리자";
        auth.authRole = "WEB";
        auth.authVal ="SYSTEM-ENGINEER";
        auth.useYn = "Y";
        auth.ord = "1";
        auth.createdId = 1L;

        Auth auth2 = new Auth();
        auth2.authNm = "운영자";
        auth2.authRole = "WEB";
        auth2.authVal ="ADMIN";
        auth2.useYn = "Y";
        auth2.ord = "2";
        auth2.createdId = 1L;

        Auth auth3 = new Auth();
        auth3.authNm = "사용자";
        auth3.authRole = "WEB";
        auth3.authVal ="USER";
        auth3.useYn = "Y";
        auth3.ord = "3";
        auth3.createdId = 1L;

        List<Auth> arr = new ArrayList<>();
        arr.add(auth);
        arr.add(auth2);
        arr.add(auth3);

        authRepository.saveAll(arr);
    }


    @Test
    void createUserAuth()  {

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        UserAuth userAuth = new UserAuth();
        userAuth.user = user;
        userAuth.auth = authRepository.findById(1L).orElseThrow(RuntimeException::new);
        userAuth.useYn ="Y";
        userAuth.createdId = 1L;

        UserAuth userAuth2 = new UserAuth();
        userAuth2.user = user;
        userAuth2.auth = authRepository.findById(2L).orElseThrow(RuntimeException::new);
        userAuth2.useYn ="Y";
        userAuth2.createdId = 1L;

        UserAuth userAuth3 = new UserAuth();
        userAuth3.user = user;
        userAuth3.auth = authRepository.findById(3L).orElseThrow(RuntimeException::new);
        userAuth3.useYn ="Y";
        userAuth3.createdId = 1L;

        List<UserAuth> arr = new ArrayList<>();
        arr.add(userAuth);
        arr.add(userAuth2);
        arr.add(userAuth3);

        userAuthRepository.saveAll(arr);
    }

    @Test
    void createCodeGrp()  {
//      --1.권한구분	AUTH_ROLE
//      --2.사용여부	USE_YN
//      --3.페이지 항목수	PAGE_PER
//      --4.게시판유형	BOARD_TYPE
//      --5.공개여부	HIDDEN_YN
//      --6.응답여부	RESPONSE_YN
//      --7.URL유형	URL_TYPE


        CodeGrp codeGrp = new CodeGrp();
        codeGrp.codeGrpNm = "권한구분";
        codeGrp.codeGrpVal = "AUTH_ROLE";
        codeGrp.useYn = "Y";
        codeGrp.createdId = 1L;

        CodeGrp codeGrp2 = new CodeGrp();
        codeGrp2.codeGrpNm = "사용여부";
        codeGrp2.codeGrpVal = "USE_YN";
        codeGrp2.useYn = "Y";
        codeGrp2.createdId = 1L;

        CodeGrp codeGrp3 = new CodeGrp();
        codeGrp3.codeGrpNm = "페이지 항목수";
        codeGrp3.codeGrpVal = "PAGE_PER";
        codeGrp3.useYn = "Y";
        codeGrp3.createdId = 1L;

        CodeGrp codeGrp4 = new CodeGrp();
        codeGrp4.codeGrpNm = "게시판 유형";
        codeGrp4.codeGrpVal = "BOARD_TYPE";
        codeGrp4.useYn = "Y";
        codeGrp4.createdId = 1L;

        CodeGrp codeGrp5 = new CodeGrp();
        codeGrp5.codeGrpNm = "공개여부";
        codeGrp5.codeGrpVal = "HIDDEN_YN";
        codeGrp5.useYn = "Y";
        codeGrp5.createdId = 1L;

        CodeGrp codeGrp6 = new CodeGrp();
        codeGrp6.codeGrpNm = "응답여부";
        codeGrp6.codeGrpVal = "RESPONSE_YN";
        codeGrp6.useYn = "Y";
        codeGrp6.createdId = 1L;

        CodeGrp codeGrp7 = new CodeGrp();
        codeGrp7.codeGrpNm = "URL유형";
        codeGrp7.codeGrpVal = "URL_TYPE";
        codeGrp7.useYn = "Y";
        codeGrp7.createdId = 1L;

        CodeGrp codeGrp8 = new CodeGrp();
        codeGrp8.codeGrpNm = "메뉴레벨";
        codeGrp8.codeGrpVal = "MENU_LV";
        codeGrp8.useYn = "Y";
        codeGrp8.createdId = 1L;

        List<CodeGrp> arr = new ArrayList<>();
        arr.add(codeGrp);
        arr.add(codeGrp2);
        arr.add(codeGrp3);
        arr.add(codeGrp4);
        arr.add(codeGrp5);
        arr.add(codeGrp6);
        arr.add(codeGrp7);
        arr.add(codeGrp8);

        codeGrpRepository.saveAll(arr);
    }

    @Test
    void createCode()  {
//      --1.권한구분	AUTH_ROLE
//      --2.사용여부	USE_YN
//      --3.페이지 항목수	PAGE_PER
//      --4.게시판유형	BOARD_TYPE
//      --5.공개여부	HIDDEN_YN
//      --6.응답여부	RESPONSE_YN
//      --7.URL유형	URL_TYPE

        Code code = new Code();
        code.codeGrp = codeGrpRepository.findById(1L).orElseThrow(RuntimeException::new);
        code.codeNm = "웹";
        code.codeVal = "WEB";
        code.useYn = "Y";
        code.createdId = 1L;
        code.ord = "1";


        Code code2 = new Code();
        code2.codeGrp = codeGrpRepository.findById(2L).orElseThrow(RuntimeException::new);
        code2.codeNm = "사용";
        code2.codeVal = "Y";
        code2.useYn = "Y";
        code2.createdId = 1L;
        code2.ord = "1";

        Code code3 = new Code();
        code3.codeGrp = codeGrpRepository.findById(2L).orElseThrow(RuntimeException::new);
        code3.codeNm = "미사용";
        code3.codeVal = "N";
        code3.useYn = "Y";
        code3.createdId = 1L;
        code3.ord = "2";



        Code code4 = new Code();
        code4.codeGrp = codeGrpRepository.findById(3L).orElseThrow(RuntimeException::new);
        code4.codeNm = "10개";
        code4.codeVal = "10";
        code4.useYn = "Y";
        code4.createdId = 1L;
        code4.ord = "1";

        Code code5 = new Code();
        code5.codeGrp = codeGrpRepository.findById(3L).orElseThrow(RuntimeException::new);
        code5.codeNm = "20개";
        code5.codeVal = "20";
        code5.useYn = "Y";
        code5.createdId = 1L;
        code5.ord = "2";

        Code code6 = new Code();
        code6.codeGrp = codeGrpRepository.findById(3L).orElseThrow(RuntimeException::new);
        code6.codeNm = "50개";
        code6.codeVal = "50";
        code6.useYn = "Y";
        code6.createdId = 1L;
        code6.ord = "3";



        Code code7 = new Code();
        code7.codeGrp = codeGrpRepository.findById(4L).orElseThrow(RuntimeException::new);
        code7.codeNm = "일반게시판";
        code7.codeVal = "POST";
        code7.useYn = "Y";
        code7.createdId = 1L;
        code7.ord = "1";

        Code code8 = new Code();
        code8.codeGrp = codeGrpRepository.findById(4L).orElseThrow(RuntimeException::new);
        code8.codeNm = "FAQ";
        code8.codeVal = "FAQ";
        code8.useYn = "Y";
        code8.createdId = 1L;
        code8.ord = "2";

        Code code9 = new Code();
        code9.codeGrp = codeGrpRepository.findById(4L).orElseThrow(RuntimeException::new);
        code9.codeNm = "QNA";
        code9.codeVal = "QNA";
        code9.useYn = "Y";
        code9.createdId = 1L;
        code9.ord = "3";



        Code code10 = new Code();
        code10.codeGrp = codeGrpRepository.findById(5L).orElseThrow(RuntimeException::new);
        code10.codeNm = "비공개";
        code10.codeVal = "Y";
        code10.useYn = "Y";
        code10.createdId = 1L;
        code10.ord = "1";

        Code code11 = new Code();
        code11.codeGrp = codeGrpRepository.findById(5L).orElseThrow(RuntimeException::new);
        code11.codeNm = "공개";
        code11.codeVal = "N";
        code11.useYn = "Y";
        code11.createdId = 1L;
        code11.ord = "2";


        Code code12 = new Code();
        code12.codeGrp = codeGrpRepository.findById(6L).orElseThrow(RuntimeException::new);
        code12.codeNm = "응답";
        code12.codeVal = "Y";
        code12.useYn = "Y";
        code12.createdId = 1L;
        code12.ord = "1";

        Code code13 = new Code();
        code13.codeGrp = codeGrpRepository.findById(6L).orElseThrow(RuntimeException::new);
        code13.codeNm = "미응답";
        code13.codeVal = "N";
        code13.useYn = "Y";
        code13.createdId = 1L;
        code13.ord = "2";

        Code code14 = new Code();
        code14.codeGrp = codeGrpRepository.findById(7L).orElseThrow(RuntimeException::new);
        code14.codeNm = "도메인";
        code14.codeVal = "DOMAIN";
        code14.useYn = "Y";
        code14.createdId = 1L;
        code14.ord = "1";

        Code code15 = new Code();
        code15.codeGrp = codeGrpRepository.findById(7L).orElseThrow(RuntimeException::new);
        code15.codeNm = "게시판";
        code15.codeVal = "BOARD";
        code15.useYn = "Y";
        code15.createdId = 1L;
        code15.ord = "2";


        Code code16 = new Code();
        code16.codeGrp = codeGrpRepository.findById(8L).orElseThrow(RuntimeException::new);
        code16.codeNm = "상위메뉴";
        code16.codeVal = "1";
        code16.useYn = "Y";
        code16.createdId = 1L;
        code16.ord = "2";

        Code code17 = new Code();
        code17.codeGrp = codeGrpRepository.findById(8L).orElseThrow(RuntimeException::new);
        code17.codeNm = "하위메뉴";
        code17.codeVal = "2";
        code17.useYn = "Y";
        code17.createdId = 1L;
        code17.ord = "3";


        List<Code> arr = new ArrayList<>();
        arr.add(code);
        arr.add(code2);
        arr.add(code3);
        arr.add(code4);
        arr.add(code5);
        arr.add(code6);
        arr.add(code7);
        arr.add(code8);
        arr.add(code9);
        arr.add(code10);
        arr.add(code11);
        arr.add(code12);
        arr.add(code13);
        arr.add(code14);
        arr.add(code15);
        arr.add(code16);
        arr.add(code17);

        codeRepository.saveAll(arr);
    }


    @Test
    void createPrnMenu()  {
        Menu menu = new Menu();
        menu.menuNm = "사용자관리";
        menu.menuLv = 1;
        menu.menuType ="NONE";
        menu.url ="";
        menu.useYn = "Y";
        menu.navYn = "Y";
        menu.authRole = "WEB";
        menu.ord = 1;
        menu.createdId = 1L;

        Menu menu2 = new Menu();
        menu2.menuNm = "메뉴관리";
        menu2.menuLv = 1;
        menu2.menuType ="NONE";
        menu2.url ="";
        menu2.useYn = "Y";
        menu2.navYn = "Y";
        menu2.authRole = "WEB";
        menu2.ord = 2;
        menu2.createdId = 1L;

        Menu menu3 = new Menu();
        menu3.menuNm = "시스템관리";
        menu3.menuLv = 1;
        menu3.menuType ="NONE";
        menu3.url ="";
        menu3.useYn = "Y";
        menu3.navYn = "Y";
        menu3.authRole = "WEB";
        menu3.ord = 3;
        menu3.createdId = 1L;

        Menu menu4 = new Menu();
        menu4.menuNm = "게시판관리";
        menu4.menuLv = 1;
        menu4.menuType ="NONE";
        menu4.url ="";
        menu4.useYn = "Y";
        menu4.navYn = "Y";
        menu4.authRole = "WEB";
        menu4.ord = 4;
        menu4.createdId = 1L;

        List<Menu> arr = new ArrayList<>();
        arr.add(menu);
        arr.add(menu2);
        arr.add(menu3);
        arr.add(menu4);

        menuRepository.saveAll(arr);

    }


    @Test
    void createChildMenu()  {
        //사용자관리
        //메뉴관리
        //시스템관리
        //게시판관리
        Menu menu = new Menu();
        menu.menuNm = "사용자정보";
        menu.menuLv = 2;
        menu.menuType ="DOMAIN";
        menu.url ="/page/user/user";
        menu.useYn = "Y";
        menu.authRole = "WEB";
        menu.ord = 1;
        menu.prnMenuId = 1L;
        menu.createdId = 1L;
        menu.navYn = "Y";


        Menu menu2 = new Menu();
        menu2.menuNm = "권한정보";
        menu2.menuLv = 2;
        menu2.menuType ="DOMAIN";
        menu2.url ="/page/user/auth";
        menu2.useYn = "Y";
        menu2.authRole = "WEB";
        menu2.ord = 2;
        menu2.prnMenuId = 1L;
        menu2.createdId = 1L;
        menu2.navYn = "Y";


        Menu menu3 = new Menu();
        menu3.menuNm = "사용자권한정보";
        menu3.menuLv = 2;
        menu3.menuType ="DOMAIN";
        menu3.url ="/page/user/userAuth";
        menu3.useYn = "Y";
        menu3.authRole = "WEB";
        menu3.ord = 3;
        menu3.prnMenuId = 1L;
        menu3.createdId = 1L;
        menu3.navYn = "Y";



        Menu menu4 = new Menu();
        menu4.menuNm = "메뉴정보";
        menu4.menuLv = 2;
        menu4.menuType ="DOMAIN";
        menu4.url ="/page/menu/menu";
        menu4.useYn = "Y";
        menu4.authRole = "WEB";
        menu4.ord = 1;
        menu4.prnMenuId = 2L;
        menu4.createdId = 1L;
        menu4.navYn = "Y";


        Menu menu5 = new Menu();
        menu5.menuNm = "메뉴권한정보";
        menu5.menuLv = 2;
        menu5.menuType ="DOMAIN";
        menu5.url ="/page/menu/menuAuth";
        menu5.useYn = "Y";
        menu5.authRole = "WEB";
        menu5.ord = 2;
        menu5.prnMenuId = 2L;
        menu5.createdId = 1L;
        menu5.navYn = "Y";



        Menu menu6 = new Menu();
        menu6.menuNm = "코드정보";
        menu6.menuLv = 2;
        menu6.menuType ="DOMAIN";
        menu6.url ="/page/mng/code";
        menu6.useYn = "Y";
        menu6.authRole = "WEB";
        menu6.ord = 1;
        menu6.prnMenuId = 3L;
        menu6.createdId = 1L;
        menu6.navYn = "Y";


        Menu menu7 = new Menu();
        menu7.menuNm = "게시판정보";
        menu7.menuLv = 2;
        menu7.menuType ="DOMAIN";
        menu7.url ="/page/board/board";
        menu7.useYn = "Y";
        menu7.authRole = "WEB";
        menu7.ord = 1;
        menu7.prnMenuId = 4L;
        menu7.createdId = 1L;
        menu7.navYn = "Y";


        Menu menu8 = new Menu();
        menu8.menuNm = "게시글정보";
        menu8.menuLv = 2;
        menu8.menuType ="DOMAIN";
        menu8.url ="/page/board/post/list/init";
        menu8.useYn = "Y";
        menu8.authRole = "WEB";
        menu8.ord = 2;
        menu8.prnMenuId = 4L;
        menu8.createdId = 1L;
        menu8.navYn = "Y";


        Menu menu9 = new Menu();
        menu9.menuNm = "FAQ정보";
        menu9.menuLv = 2;
        menu9.menuType ="DOMAIN";
        menu9.url ="/page/board/faq/list/init";
        menu9.useYn = "Y";
        menu9.authRole = "WEB";
        menu9.ord = 3;
        menu9.prnMenuId = 4L;
        menu9.createdId = 1L;
        menu9.navYn = "Y";


        Menu menu10 = new Menu();
        menu10.menuNm = "QNA정보";
        menu10.menuLv = 2;
        menu10.menuType ="DOMAIN";
        menu10.url ="/page/board/qna/list/init";
        menu10.useYn = "Y";
        menu10.authRole = "WEB";
        menu10.ord = 4;
        menu10.prnMenuId = 4L;
        menu10.createdId = 1L;
        menu10.navYn = "Y";


        List<Menu> arr = new ArrayList<>();
        arr.add(menu);
        arr.add(menu2);
        arr.add(menu3);
        arr.add(menu4);
        arr.add(menu5);
        arr.add(menu6);
        arr.add(menu7);
        arr.add(menu8);
        arr.add(menu9);
        arr.add(menu10);

        menuRepository.saveAll(arr);
    }

    @Test
    void createMenuAuth()  {
        Auth auth =  authRepository.findById(1L).orElseThrow(RuntimeException::new);

        MenuAuth menuAuth = new MenuAuth();
        menuAuth.auth = auth;
        menuAuth.menu = menuRepository.findById(1L).orElseThrow(RuntimeException::new);
        menuAuth.createdId = 1L;
        menuAuth.useYn = "Y";

        MenuAuth menuAuth2 = new MenuAuth();
        menuAuth2.auth = auth;
        menuAuth2.menu = menuRepository.findById(2L).orElseThrow(RuntimeException::new);
        menuAuth2.createdId = 1L;
        menuAuth2.useYn = "Y";

        MenuAuth menuAuth3 = new MenuAuth();
        menuAuth3.auth = auth;
        menuAuth3.menu = menuRepository.findById(3L).orElseThrow(RuntimeException::new);
        menuAuth3.createdId = 1L;
        menuAuth3.useYn = "Y";

        MenuAuth menuAuth4 = new MenuAuth();
        menuAuth4.auth = auth;
        menuAuth4.menu = menuRepository.findById(4L).orElseThrow(RuntimeException::new);
        menuAuth4.createdId = 1L;
        menuAuth4.useYn = "Y";

        MenuAuth menuAuth5 = new MenuAuth();
        menuAuth5.auth = auth;
        menuAuth5.menu = menuRepository.findById(5L).orElseThrow(RuntimeException::new);
        menuAuth5.createdId = 1L;
        menuAuth5.useYn = "Y";

        MenuAuth menuAuth6 = new MenuAuth();
        menuAuth6.auth = auth;
        menuAuth6.menu = menuRepository.findById(6L).orElseThrow(RuntimeException::new);
        menuAuth6.createdId = 1L;
        menuAuth6.useYn = "Y";

        MenuAuth menuAuth7 = new MenuAuth();
        menuAuth7.auth = auth;
        menuAuth7.menu = menuRepository.findById(7L).orElseThrow(RuntimeException::new);
        menuAuth7.createdId = 1L;
        menuAuth7.useYn = "Y";

        MenuAuth menuAuth8 = new MenuAuth();
        menuAuth8.auth = auth;
        menuAuth8.menu = menuRepository.findById(8L).orElseThrow(RuntimeException::new);
        menuAuth8.createdId = 1L;
        menuAuth8.useYn = "Y";

        MenuAuth menuAuth9 = new MenuAuth();
        menuAuth9.auth = auth;
        menuAuth9.menu = menuRepository.findById(9L).orElseThrow(RuntimeException::new);
        menuAuth9.createdId = 1L;
        menuAuth9.useYn = "Y";

        MenuAuth menuAuth10 = new MenuAuth();
        menuAuth10.auth = auth;
        menuAuth10.menu = menuRepository.findById(10L).orElseThrow(RuntimeException::new);
        menuAuth10.createdId = 1L;
        menuAuth10.useYn = "Y";

        MenuAuth menuAuth11 = new MenuAuth();
        menuAuth11.auth = auth;
        menuAuth11.menu = menuRepository.findById(11L).orElseThrow(RuntimeException::new);
        menuAuth11.createdId = 1L;
        menuAuth11.useYn = "Y";

        MenuAuth menuAuth12 = new MenuAuth();
        menuAuth12.auth = auth;
        menuAuth12.menu = menuRepository.findById(12L).orElseThrow(RuntimeException::new);
        menuAuth12.createdId = 1L;
        menuAuth12.useYn = "Y";

        MenuAuth menuAuth13 = new MenuAuth();
        menuAuth13.auth = auth;
        menuAuth13.menu = menuRepository.findById(13L).orElseThrow(RuntimeException::new);
        menuAuth13.createdId = 1L;
        menuAuth13.useYn = "Y";

        MenuAuth menuAuth14 = new MenuAuth();
        menuAuth14.auth = auth;
        menuAuth14.menu = menuRepository.findById(14L).orElseThrow(RuntimeException::new);
        menuAuth14.createdId = 1L;
        menuAuth14.useYn = "Y";

        List<MenuAuth> arr = new ArrayList<>();
        arr.add(menuAuth);
        arr.add(menuAuth2);
        arr.add(menuAuth3);
        arr.add(menuAuth4);
        arr.add(menuAuth5);
        arr.add(menuAuth6);
        arr.add(menuAuth7);
        arr.add(menuAuth8);
        arr.add(menuAuth9);
        arr.add(menuAuth10);
        arr.add(menuAuth11);
        arr.add(menuAuth12);
        arr.add(menuAuth13);
        arr.add(menuAuth14);

        menuAuthRepository.saveAll(arr);
    }


}
