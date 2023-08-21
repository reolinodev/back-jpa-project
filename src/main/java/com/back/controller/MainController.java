package com.back.controller;


import com.back.domain.dto.MainMenuIF;
import com.back.domain.dto.MainUserDto;
import com.back.domain.dto.MenuDto;
import com.back.domain.params.MenuParam;
import com.back.service.MenuService;
import com.back.support.ResponseUtils;
import com.back.token.JwtUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final MenuService menuService;
    private final JwtUtils jwtUtils;

    //인증된 사용자 정보를 가져온다.
    @GetMapping("/user")
    public ResponseEntity<Map<String,Object>> getMainUser(HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        String message ="정상적으로 조회되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        MainUserDto mainUserDto = new MainUserDto();
        mainUserDto.userId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        mainUserDto.authId = jwtUtils.getTokenAuthId(jwtUtils.resolveToken(httpServletRequest));
        mainUserDto.loginId = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"loginId");
        mainUserDto.userNm = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"userNm");

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", mainUserDto);

        return new ResponseEntity<> (responseMap,  status);
    }

    //메뉴목록을 조회한다.
    @GetMapping("/nav/{authId}")
    public ResponseEntity<Map<String,Object>> getNavs(@PathVariable Long authId, HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();

        String message ="정상적으로 조회되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        MainMenuIF menuUrl = menuService.getMainMenu(authId);

        MenuParam menuParam = new MenuParam();
        menuParam.authId = authId;
        menuParam.useYn = "Y";
        menuParam.menuLv = 1;
        menuParam.authRole = "WEB";

        List<MenuDto> menuLv1List = menuService.getMenusByAuth(menuParam);

        menuParam.menuLv = 2;
        List<MenuDto> menuLv2List = menuService.getMenusByAuth(menuParam);
        data.put("menuUrl", menuUrl);
        data.put("menuLv1List", menuLv1List);
        data.put("menuLv2List", menuLv2List);

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", data);

        return new ResponseEntity<> (responseMap,  status);
    }
}
