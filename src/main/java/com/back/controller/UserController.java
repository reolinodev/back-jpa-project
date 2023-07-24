package com.back.controller;


import com.back.domain.User;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.UserDto;
import com.back.domain.params.UserParam;
import com.back.service.UserService;
import com.back.support.ResponseUtils;

import java.util.LinkedHashMap;
import java.util.List;
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
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
//    private final JwtUtils jwtUtils;

    //"사용자를 전체 조회한다.
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getUsers(
        @RequestBody UserParam userParam, HttpServletRequest httpServletRequest){
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        Page<UserDto> getUsersResult = userService.getUsers(userParam);
        Long totalCount = getUsersResult.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getUsersResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    //사용자를 상세 조회한다.
    @GetMapping("/{id}")
    public ResponseEntity <Map<String,Object>> getUser(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        UserDto getUserResult = userService.getUser(id);

        int count = 0;
        if (!"".equals(getUserResult.loginId)) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getUserResult);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }


    //사용자를 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createUser(
        @Validated({ValidationGroups.UserCreateGroup.class}) @RequestBody UserParam userParam, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자가 생성이 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int checkLoginIdResult = userService.checkLoginId(userParam.loginId);

        if(checkLoginIdResult > 0) {
            message = "해당 아이디가 존재합니다.";
            code ="fail";
            status = HttpStatus.BAD_REQUEST;
        } else {
//            userEntity.created_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
            userParam.userPw = userParam.loginId;
            User createUserResult = userService.createUser(userParam);

            if(!userParam.userNm.equals(createUserResult.userNm)){
                message ="정상적으로 생성이 되지 않았습니다.";
                code = "bad request";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //"사용자를 수정한다
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(
        @Validated(ValidationGroups.UserUpdateGroup.class) @RequestBody UserParam userParam, @PathVariable Long id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자 정보가 수정이 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

//        userEntity.updated_id = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
        User updateUserResult = userService.updateUser(userParam, id);

        if(!id.equals(updateUserResult.id)){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "bad request";
            status = HttpStatus.BAD_REQUEST;

        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //사용자를 삭제한다. => 실제로는 사용여부를 N으로 변경
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자가 삭제 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        //토큰에서 값 추출
        Long updatedId = 1L;

        User deleteUserResult = userService.deleteUser(id, updatedId);

        if(!"N".equals(deleteUserResult.useYn)){
            message ="정상적으로 삭제 되지않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //사용자 비밀번호를 초기화한다
    @GetMapping("/init-user-pw/{id}")
    public ResponseEntity<Map<String,Object>> initUserPw(@PathVariable Long id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자의 비밀번호가 초기화 되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        //토큰에서 값 추출
        Long updatedId = 1L;

        User updateUserPwResult = userService.initUserPw(id, updatedId);

        if(updateUserPwResult.loginFailCnt != 0){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //사용자의 잠금상태를 초기화한다.
    @GetMapping("/init-login-fail-cnt/{id}")
    public ResponseEntity<Map<String,Object>> initLoginFailCnt(@PathVariable Long id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자의 잠금이 해제되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        //토큰에서 값 추출
        Long updatedId = 1L;

        User initLoginFailCntResult = userService.initLoginFailCnt(id, updatedId);

        if(initLoginFailCntResult.loginFailCnt != 0){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }


    //사용자 비밀번호 변경페이지에서 사용자 비밀번호를 수정한다
    @PutMapping("/user-pw")
    public ResponseEntity<Map<String,Object>> updateUserPw(
        @Validated(ValidationGroups.UserPwUpdateGroup.class) @RequestBody UserParam userParam, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "사용자의 비밀번호가 변경되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;


        UserDto getUserByLoginIdResult = userService.getUserByLoginId(userParam.loginId);

        if(getUserByLoginIdResult == null) {
            message = "해당 아이디가 존재하지 않습니다.";
            code ="fail";
            status = HttpStatus.BAD_REQUEST;
        } else {

            User updateUserPwResult = userService.updateUserPw(userParam, getUserByLoginIdResult.userId);

            if(!"Y".equals(updateUserPwResult.pwInitYn)){
                message ="사용자의 비밀번호가 정상적으로 변경하지 않았습니다.";
                code = "bad request";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));



//        String userId = jwtUtils.getTokenInfo(jwtUtils.resolveToken(httpServletRequest),"user_id");
//        userEntity.updated_id = userId;
//        userEntity.user_id = userId;
//        userEntity.pw_init_yn = "Y";
//        userEntity.pw_fail_cnt = 0;
//
//        int result = userService.updateUser(userEntity);
//
//        if(result < 1){
//            message ="정상적으로 수정이 되지않았습니다.";
//            code = "fail";
//            status = HttpStatus.BAD_REQUEST;
//        }
//
//        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
//        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
