package com.back.controller;

import com.back.domain.dto.MenuAuthDto;
import com.back.domain.params.MenuAuthParam;
import com.back.service.MenuAuthService;
import com.back.support.ResponseUtils;
import com.back.token.JwtUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menuAuth")
@RequiredArgsConstructor
public class MenuAuthController {

    private final MenuAuthService menuAuthService;
    private final JwtUtils jwtUtils;

    //메뉴별 권한의 리스트를 조회한다.
    @GetMapping("/{authRole}/{menuId}")
    public ResponseEntity<Map<String,Object>> getMenuAuths(@PathVariable String authRole,@PathVariable Long menuId,HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<MenuAuthDto> getMenuAuthsResult = menuAuthService.getMenuAuths(menuId,authRole);

        String message = getMenuAuthsResult.size()+" 개가 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getMenuAuthsResult);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Map<String,Object>> saveMenuAuths(@PathVariable Long menuId,
        @RequestBody MenuAuthParam menuAuthParam, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        menuAuthParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        menuAuthParam.menuId = menuId;

        menuAuthService.saveMenuAuths(menuAuthParam);

        String message = "메뉴별권한을 수정하였습니다";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }
}
