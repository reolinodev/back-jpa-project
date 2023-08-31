package com.back.controller;

import com.back.domain.dto.AuthDto;
import com.back.domain.dto.BoardDto;
import com.back.domain.dto.CodeDto;
import com.back.domain.dto.MenuDto;
import com.back.domain.dto.MyAuthDto;
import com.back.domain.params.UserAuthParam;
import com.back.service.AuthService;
import com.back.service.BoardService;
import com.back.service.CodeService;
import com.back.service.MenuService;
import com.back.service.UserAuthService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {

    private final CodeService codeService;
    private final AuthService authService;
    private final UserAuthService userAuthService;
    private final MenuService menuService;
    private final BoardService boardService;

    //코드그룹값에 해당하는 항목을 조회한다.
    @GetMapping("/code/{codeGrpVal}")
    public ResponseEntity<Map<String,Object>> getItemCodes(@PathVariable String codeGrpVal, HttpServletRequest httpServletRequest){
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<CodeDto> getItemCodesResult = codeService.getItemCodes(codeGrpVal);

        String message = getItemCodesResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getItemCodesResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //사용가능한 권한의 리스트를 조회한다
    @GetMapping("/auth/used-auths")
    public ResponseEntity <Map<String,Object>> getItemUsedAuths(HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        List<AuthDto> getUsedAuthsResult = authService.getUsedAuths();

        String message = getUsedAuthsResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getUsedAuthsResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //사용가능한 권한구분별 권한을 조회한다.
    @PostMapping("/auth/auth-roles/{authRole}")
    public ResponseEntity <Map<String,Object>> getItemAuthRoles(@PathVariable String authRole, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<AuthDto> getItemAuthRolesResult = authService.getItemAuthRoles(authRole);

        String message = getItemAuthRolesResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getItemAuthRolesResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //내가 가진 권한을 가져온다.
    @PostMapping("/auth/mine")
    public ResponseEntity <Map<String,Object>> getItemMyAuths(
        @RequestBody UserAuthParam userAuthParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<MyAuthDto> getItemMyAuthsResult = userAuthService.getItemMyAuths(userAuthParam);

        String message = getItemMyAuthsResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getItemMyAuthsResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    //상위의 메뉴들을 조회한다.
    @PostMapping("/menu/prn-menu/{authRole}")
    public ResponseEntity<Map<String,Object>> getItemPrnMenus(@PathVariable String authRole, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<MenuDto> getItemMyAuthsResult = menuService.getItemPrnMenus(authRole);

        String message = getItemMyAuthsResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getItemMyAuthsResult);


        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }


    //사용가능한 게시판의 목록을 가져온다.
    @PostMapping("/board/{boardType}")
    public ResponseEntity <Map<String,Object>> getItemUsedBoards(@PathVariable String boardType, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        List<BoardDto> getItemUsedBoardsResult = boardService.getItemUsedBoards(boardType);

        String message = getItemUsedBoardsResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getItemUsedBoardsResult);


        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

}
