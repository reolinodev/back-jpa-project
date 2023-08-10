package com.back.service;

import com.back.domain.Auth;
import com.back.domain.Code;
import com.back.domain.CodeGrp;
import com.back.domain.Menu;
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

        authRepository.saveAll(List.of(auth, auth2, auth3));
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

        userAuthRepository.saveAll(List.of(userAuth, userAuth2, userAuth3));
    }

    @Test
    void createCodeGrp()  {
//      --1.권한구분	AUTH_ROLE
//      --2.사용여부	USE_YN
//      --3.페이지 항목수	PAGE_PER
//      --4.게시판유형	BOARD_TYPE
//      --5.공개여부	HIDDEN_YN
//      --6.응답여부	RESPONSE_YN

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

        codeGrpRepository.saveAll(List.of(codeGrp, codeGrp2, codeGrp3, codeGrp4, codeGrp5, codeGrp6));

    }

    @Test
    void createCode()  {
//      --1.권한구분	AUTH_ROLE
//      --2.사용여부	USE_YN
//      --3.페이지 항목수	PAGE_PER
//      --4.게시판유형	BOARD_TYPE
//      --5.공개여부	HIDDEN_YN
//      --6.응답여부	RESPONSE_YN

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

        codeRepository.saveAll(List.of(code, code2, code3, code4, code5, code6, code7, code8, code9, code10, code11, code12, code13));
    }


    @Test
    void createPrnMenu()  {
        Menu menu = new Menu();
        menu.menuNm = "사용자관리";
        menu.menuLv = 1;
        menu.menuType ="NONE";
        menu.url ="";
        menu.useYn = "Y";
        menu.authRole = "WEB";
        menu.ord = 1;
        menu.createdId = 1L;

        Menu menu2 = new Menu();
        menu2.menuNm = "메뉴관리";
        menu2.menuLv = 1;
        menu2.menuType ="NONE";
        menu2.url ="";
        menu2.useYn = "Y";
        menu2.authRole = "WEB";
        menu2.ord = 2;
        menu2.createdId = 1L;

        Menu menu3 = new Menu();
        menu3.menuNm = "시스템관리";
        menu3.menuLv = 1;
        menu3.menuType ="NONE";
        menu3.url ="";
        menu3.useYn = "Y";
        menu3.authRole = "WEB";
        menu3.ord = 3;
        menu3.createdId = 1L;

        Menu menu4 = new Menu();
        menu4.menuNm = "게시판관리";
        menu4.menuLv = 1;
        menu4.menuType ="NONE";
        menu4.url ="";
        menu4.useYn = "Y";
        menu4.authRole = "WEB";
        menu4.ord = 4;
        menu4.createdId = 1L;

        menuRepository.saveAll(List.of(menu, menu2, menu3, menu4));
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
        menu.url ="";
        menu.useYn = "Y";
        menu.authRole = "WEB";
        menu.ord = 1;
        menu.prnMenuId = 1L;
        menu.createdId = 1L;

        Menu menu2 = new Menu();
        menu2.menuNm = "권한정보";
        menu2.menuLv = 2;
        menu2.menuType ="DOMAIN";
        menu2.url ="";
        menu2.useYn = "Y";
        menu2.authRole = "WEB";
        menu2.ord = 2;
        menu2.prnMenuId = 1L;
        menu2.createdId = 1L;

        Menu menu3 = new Menu();
        menu3.menuNm = "사용자권한정보";
        menu3.menuLv = 2;
        menu3.menuType ="DOMAIN";
        menu3.url ="";
        menu3.useYn = "Y";
        menu3.authRole = "WEB";
        menu3.ord = 3;
        menu3.prnMenuId = 1L;
        menu3.createdId = 1L;



        Menu menu4 = new Menu();
        menu4.menuNm = "메뉴정보";
        menu4.menuLv = 2;
        menu4.menuType ="DOMAIN";
        menu4.url ="";
        menu4.useYn = "Y";
        menu4.authRole = "WEB";
        menu4.ord = 1;
        menu4.prnMenuId = 2L;
        menu4.createdId = 1L;

        Menu menu5 = new Menu();
        menu5.menuNm = "메뉴권한정보";
        menu5.menuLv = 2;
        menu5.menuType ="DOMAIN";
        menu5.url ="";
        menu5.useYn = "Y";
        menu5.authRole = "WEB";
        menu5.ord = 2;
        menu5.prnMenuId = 2L;
        menu5.createdId = 1L;


        Menu menu6 = new Menu();
        menu6.menuNm = "코드정보";
        menu6.menuLv = 2;
        menu6.menuType ="DOMAIN";
        menu6.url ="";
        menu6.useYn = "Y";
        menu6.authRole = "WEB";
        menu6.ord = 1;
        menu6.prnMenuId = 3L;
        menu6.createdId = 1L;

        Menu menu7 = new Menu();
        menu7.menuNm = "게시판정보";
        menu7.menuLv = 2;
        menu7.menuType ="DOMAIN";
        menu7.url ="";
        menu7.useYn = "Y";
        menu7.authRole = "WEB";
        menu7.ord = 1;
        menu7.prnMenuId = 4L;
        menu7.createdId = 1L;

        Menu menu8 = new Menu();
        menu8.menuNm = "게시글정보";
        menu8.menuLv = 2;
        menu8.menuType ="DOMAIN";
        menu8.url ="";
        menu8.useYn = "Y";
        menu8.authRole = "WEB";
        menu8.ord = 2;
        menu8.prnMenuId = 4L;
        menu8.createdId = 1L;

        Menu menu9 = new Menu();
        menu9.menuNm = "FAQ정보";
        menu9.menuLv = 2;
        menu9.menuType ="DOMAIN";
        menu9.url ="";
        menu9.useYn = "Y";
        menu9.authRole = "WEB";
        menu9.ord = 3;
        menu9.prnMenuId = 4L;
        menu9.createdId = 1L;

        Menu menu10 = new Menu();
        menu10.menuNm = "QNA정보";
        menu10.menuLv = 2;
        menu10.menuType ="DOMAIN";
        menu10.url ="";
        menu10.useYn = "Y";
        menu10.authRole = "WEB";
        menu10.ord = 4;
        menu10.prnMenuId = 4L;
        menu10.createdId = 1L;

        menuRepository.saveAll(List.of(menu, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10));
    }


}
