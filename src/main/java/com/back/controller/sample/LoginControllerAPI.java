package com.back.controller.sample;

import com.back.domain.common.ValidationGroups;
import com.back.domain.sample.Login;
import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.User;
import com.back.service.sample.LoginService;
import com.back.support.ResponseUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sample/login")
public class LoginControllerAPI {

    private final LoginService loginService;

    @PostMapping("/proc")
    public ResponseEntity<Map<String,Object>> excuteLogin (
        @Validated(ValidationGroups.LoginGroup.class) @RequestBody Login login, HttpServletRequest httpServletRequest) throws Exception {
        Map <String,Object> responseMap = new HashMap<>();
        String message = "";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        User loginData = loginService.checkExistUser(login);
        int loginCnt = loginService.checkUserPw(login);

        boolean chkLogin = loginCnt != 0;

        if(loginData == null){
            message = "아이디가 존재하지 않습니다.";
            code = "unauthorized";
            status = HttpStatus.UNAUTHORIZED;
            responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
            return new ResponseEntity<>(responseMap, status);
        }
        else if(!chkLogin){
            message = "비밀번호가 일치하지 않습니다.";
            code = "unauthorized";
            status = HttpStatus.UNAUTHORIZED;

            loginService.updateLoginFailCnt(loginData, chkLogin);

            responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
            return new ResponseEntity<>(responseMap, status);
        }
        else if(loginData.loginFailCnt > 5){
            message = "비밀번호 입력을 5회이상 실패하셨습니다. 관리자에게 문의 바랍니다.";
            code = "unauthorized";
            status = HttpStatus.UNAUTHORIZED;

            responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
            return new ResponseEntity<>(responseMap, status);
        }else {
            message = "메인화면으로 이동합니다.";
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.user = loginData;
            loginHistory.device = login.device;

            loginService.updateLoginFailCnt(loginData, chkLogin);
            loginService.createLoginHistory(loginHistory);

            LinkedHashMap<String,Object> data = new LinkedHashMap<>();
            data.put("id", loginData.id);
            data.put("userNm", loginData.userNm);
            data.put("loginId", loginData.loginId);
            data.put("telNo", loginData.telNo);

            responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
            responseMap.put("data", data);

            return new ResponseEntity<>(responseMap, status);
        }
    }


    @PutMapping("/user-pw")
    public ResponseEntity<Map<String,Object>> updateUserPw (
        @Validated(ValidationGroups.UserPwUpdateGroup.class) @RequestBody Login login, HttpServletRequest httpServletRequest) throws Exception {
        Map <String,Object> responseMap = new HashMap<>();
        String message = "";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        User loginData = loginService.checkExistUser(login);

        if(loginData == null){
            message = "아이디가 존재하지 않습니다.";
            code = "unauthorized";
            status = HttpStatus.UNAUTHORIZED;
        } else {
            loginService.updateUserPw(login, loginData);
            message = "비밀번호가 변경되었습니다.";
        }
        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        return new ResponseEntity<>(responseMap, status);
    }

}
