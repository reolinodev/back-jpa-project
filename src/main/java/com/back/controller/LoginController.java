package com.back.controller;


import com.back.domain.LoginHistory;
import com.back.domain.User;
import com.back.domain.common.JwtHeader;
import com.back.domain.dto.LoginDto;
import com.back.domain.params.LoginParam;
import com.back.service.LoginService;
import com.back.support.ResponseUtils;
import com.back.token.JwtTokenProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    private final JwtTokenProvider jwtTokenProvider;

    //인증키를 발급하고 로그인 히스토리를 저장한다.
    @PostMapping("/certification")
    public ResponseEntity<Map<String,Object>> certification (
        @RequestBody LoginParam loginParam, HttpServletRequest httpServletRequest) throws Exception {

        JwtHeader jwtHeader;
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        HttpStatus status = HttpStatus.OK;
        String message = "";
        String code = "ok";
        String accessToken = "";
        String refreshToken = "";

        int countLoginId = loginService.checkLoginId(loginParam);

        if(countLoginId == 0){
            message = "아이디가 존재하지 않습니다.";
            code = "unauthorized";
            status = HttpStatus.UNAUTHORIZED;
            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
            responseMap.put("header", jwtHeader);
            return new ResponseEntity<>(responseMap, status);
        }

        //패스워드 체크
        int countUserPw = loginService.checkUserPw(loginParam);
        if(countUserPw == 0){
            message = "비밀번호가 일치하지 않습니다.";
            code = "unauthorized";
            status = HttpStatus.UNAUTHORIZED;

            loginService.updateLoginFailCnt(loginParam);

            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
            responseMap.put("header", jwtHeader);
            return new ResponseEntity<>(responseMap, status);
        }

        LoginDto loginDto = loginService.getLoginUser(loginParam.loginId);
//
//        if(loginData == null){
//            message = "접속 권한이 없습니다.";
//            code = "unauthorized";
//            status = HttpStatus.UNAUTHORIZED;
//
//            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
//            responseMap.put("header", jwtHeader);
//            return new ResponseEntity<>(responseMap, status);
//        }
//        else if(loginData.pw_fail_cnt > 5){
//            message = "비밀번호 입력을 5회이상 실패하셨습니다. 관리자에게 문의 바랍니다.";
//            code = "unauthorized";
//            status = HttpStatus.UNAUTHORIZED;
//
//            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
//            responseMap.put("header", jwtHeader);
//            return new ResponseEntity<>(responseMap, status);
//        }else if("".equals(loginData.auth_id)){
//            message = "사용자의 권한이 존재하지 않습니다. 관리자에게 문의 바랍니다.";
//            code = "unauthorized";
//            status = HttpStatus.UNAUTHORIZED;
//
//            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
//            responseMap.put("header", jwtHeader);
//            return new ResponseEntity<>(responseMap, status);
//        }
//
        message = "인증키가 생성되었습니다.";
        accessToken = jwtTokenProvider.generateToken(loginDto);
        refreshToken = jwtTokenProvider.generateRefreshToken(loginDto);

        loginParam.accessToken = accessToken;
        loginParam.refreshToken = refreshToken;
        loginParam.userId = loginDto.userId;
        LoginHistory saveTokenResult = loginService.saveToken(loginParam);
        if(!saveTokenResult.accessToken.equals(accessToken)){
            message = "사용자 히스토리가 정상적으로 저장되지 않았습니다.";
            code = "bad request";
            status = HttpStatus.BAD_REQUEST;
            jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);
            responseMap.put("header", jwtHeader);
            return new ResponseEntity<>(responseMap, status);
        }

        jwtHeader = ResponseUtils.setJwtHeader(message, code, accessToken, refreshToken, httpServletRequest);

        responseMap.put("header", jwtHeader);
        return new ResponseEntity<>(responseMap, status);
    }

    //로그인을 한다. 비밀번호 초기화가 안되어 있으면 비밀번호 수정하는 페이지로 이동한다. 로그인을 성공하면 최종 로그인 일시를 남긴다.
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login (@RequestBody LoginParam loginParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "메인화면으로 이동합니다";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        LoginDto loginInfo = loginService.getLoginUser(loginParam.loginId);
        String pwInitYn = loginInfo.pwInitYn;

        //초기화를 안했을때
        if("N".equals(pwInitYn)) {
            message = "비밀번호 초기화가 필요합니다. 비밀번호 변경 화면으로 이동합니다.";
            code = "pwchange";
        }
        if("N".equals(pwInitYn)) {
            message = "비밀번호 초기화가 필요합니다. 비밀번호 변경 화면으로 이동합니다.";
            code = "pwchange";
        }
        else{
            User result = loginService.updateLastLoginDt(loginInfo.userId);

            if(!Objects.equals(result.id, loginInfo.userId)){
                message = "로그인에 실패하였습니다.";
                code = "unauthorized";
                status = HttpStatus.UNAUTHORIZED;
            }
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", loginInfo);

        return new ResponseEntity<>(responseMap, status);
    }

}
