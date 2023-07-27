package com.back.controller;

import com.back.domain.Menu;
import com.back.domain.common.Header;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.MenuDto;
import com.back.domain.dto.MenuTreeIF;
import com.back.domain.params.MenuParam;
import com.back.service.MenuService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
//    private final MenuAuthService menuAuthService;
    private final JwtUtils jwtUtils;

    //트리구조로 메뉴리스트를 가져온다.")
    @GetMapping("/menu-tree/{authRole}")
    public ResponseEntity<Map<String,Object>> getMenuTree(@PathVariable String authRole,HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<MenuTreeIF> getMenuTreeResult = menuService.getMenuTree(authRole);

        String message = getMenuTreeResult.size()+" 개가 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getMenuTreeResult);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }

    //메뉴를 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createMenu(@Validated(ValidationGroups.MenuCreateGroup.class) @RequestBody MenuParam menuParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        menuParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        Menu createMenuResult = menuService.createMenu(menuParam);

        String message = "메뉴가 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        if(!createMenuResult.menuNm.equals(menuParam.menuNm)){
            message ="메뉴 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //메뉴를 상세 조회한다.
    @GetMapping("/{id}")
    public ResponseEntity <Map<String,Object>> getMenu(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();
        MenuDto getMenuResult = menuService.getMenu(id);
        int count = 0;
        if (getMenuResult != null) count= 1;

        String message = count+" 개가 조회되었습니다..";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getMenuResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //메뉴 정보를 수정한다
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateMenu(@PathVariable Long id,
        @Validated(ValidationGroups.MenuUpdateGroup.class) @RequestBody MenuParam menuParam,
        HttpServletRequest httpServletRequest) {

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        menuParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        menuService.updateMenu(id, menuParam);

        String message = "메뉴가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));


        return new ResponseEntity<>(responseMap, status);
    }

    //메뉴를 삭제한다
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteMenu(@PathVariable Long id, HttpServletRequest httpServletRequest) {

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        String message = "메뉴가 삭제 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        int checkChildMenuResult = menuService.checkChildMenu(id);

        if(checkChildMenuResult != 0){
            message ="하위메뉴가 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else{
            //menuAuthService.deleteMenuAuth(menuEntity.menu_id);
            //menuAuth cascade 처리
            menuService.deleteMenu(id);
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

}
