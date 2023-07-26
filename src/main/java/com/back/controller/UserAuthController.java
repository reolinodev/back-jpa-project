package com.back.controller;

import com.back.domain.UserAuth;
import com.back.domain.dto.UserAuthDto;
import com.back.domain.dto.UserAuthInputDto;
import com.back.domain.params.UserAuthParam;
import com.back.service.UserAuthService;
import com.back.support.ResponseUtils;

import com.back.token.JwtUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;
    private final JwtUtils jwtUtils;

    //사용자의 권한을 전체 조회한다.
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getUserAuths(
        @RequestBody UserAuthParam userAuthParam, HttpServletRequest httpServletRequest){
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        Page<UserAuthDto> getUserAuthsResult = userAuthService.getUserAuths(userAuthParam);
        Long totalCount = getUserAuthsResult.getTotalElements();

        String message = totalCount+" 개가 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getUserAuthsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //권한을 입력할 대상을 전체 조회한다.
    @PostMapping("/input-user")
    public ResponseEntity<Map<String,Object>> getInputUserAuths(
        @RequestBody UserAuthParam userAuthParam, HttpServletRequest httpServletRequest){
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        Page<UserAuthInputDto> getInputUserAuthsResult = userAuthService.getInputUserAuths(userAuthParam);
        Long totalCount = getInputUserAuthsResult.getTotalElements();

        String message = totalCount+" 개가 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getInputUserAuthsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //사용자의 권한을 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createUserAuths(
        @RequestBody UserAuthParam userAuthParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        userAuthParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        String message = "사용자의 권한이 부여되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        List<UserAuth> createUserAuthsResult = userAuthService.createUserAuths(userAuthParam);

        if(createUserAuthsResult.size() != userAuthParam.userArr.length){
            message ="사용자 권한 부여에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //사용자의 권한을 삭제한다. 실제로는 사용여부를 N으로 변경한다.
    @DeleteMapping("")
    public ResponseEntity<Map<String,Object>> deleteUserAuth(
        @RequestBody UserAuthParam userAuthParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        userAuthParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        List<UserAuth> deleteUserAuthResult = userAuthService.deleteUserAuth(userAuthParam);

        String message = "사용자의 권한이 삭제되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(deleteUserAuthResult.size() != userAuthParam.userArr.length){
            message ="사용자 권한 삭제에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }
}
