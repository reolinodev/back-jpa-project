package com.back.controller;

import com.back.domain.Auth;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.AuthDto;
import com.back.domain.params.AuthParam;
import com.back.service.AuthService;
import com.back.support.ResponseUtils;
import com.back.token.JwtUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    //권한을 전체 조회한다
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getAuths(@RequestBody AuthParam authParam, HttpServletRequest httpServletRequest){
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        Page<AuthDto> getAuthsResult = authService.getAuths(authParam);
        Long totalCount = getAuthsResult.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getAuthsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //권한을 입력한다
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createAuth(
        @Validated(ValidationGroups.AuthCreateGroup.class) @RequestBody AuthParam authParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "권한이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int count = authService.checkAuthVal(authParam);

        if(count > 0){
            message ="같은 코드가 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else {

            authParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
            Auth createAuthResult = authService.createAuth(authParam);

            if(!createAuthResult.authNm.equals(authParam.authNm)){
                message ="권한 생성에 실패하였습니다.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //권한 정보를 상세 조회한다
    @GetMapping("/{id}")
    public ResponseEntity <Map<String,Object>> getAuth(@PathVariable Long id, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        AuthDto getAuthResult = authService.getAuth(id);
        int count = 0;
        if (getAuthResult != null) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getAuthResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //권한정보를 수정한다
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateAuth(@PathVariable long id,
        @Validated(ValidationGroups.AuthUpdateGroup.class) @RequestBody AuthParam authParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        authParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        Auth updateAuthResult = authService.updateAuth(id, authParam);

        String message = "권한이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if("".equals(updateAuthResult.authVal)){
            message ="권한 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //권한을 삭제한다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteAuth(@PathVariable long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "권한이 삭제 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        Long updatedId =  jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        Auth deleteAuthResult = authService.deleteAuth(id, updatedId);

        if(!"N".equals(deleteAuthResult.useYn)){
            message ="정상적으로 삭제 되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }
}
