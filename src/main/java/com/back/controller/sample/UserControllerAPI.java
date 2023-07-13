package com.back.controller.sample;

import com.back.domain.sample.User;
import com.back.domain.common.Header;
import com.back.domain.common.ValidationGroups;
import com.back.domain.sample.UserDto;
import com.back.domain.sample.UserHistory;
import com.back.domain.sample.UserMapping;
import com.back.service.sample.LoginService;
import com.back.service.sample.UserService;
import com.back.support.ResponseUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sample/user")
public class UserControllerAPI implements Serializable {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getUsers(
        @RequestBody UserDto params, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();

        Page<UserMapping> getUsers = userService.getUsers(params);
        Long totalCount = getUsers.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("totalCount", totalCount);
        responseMap.put("data", getUsers.getContent());

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity <Map<String,Object>> getUser(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();
        UserMapping getUser = userService.getUser(id);

        String message = "1건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", getUser);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createUser(
        @Validated(ValidationGroups.UserCreateGroup.class) @RequestBody User params, HttpServletRequest httpServletRequest){
        Map <String,Object> responseMap = new HashMap<>();
        String message;
        String code;
        HttpStatus status;

        User checkUser = userService.checkUser(params.loginId);

        if(checkUser != null){
            message = "같은 아이디가 존재합니다.";
            code = "bad request";
            status = HttpStatus.BAD_REQUEST;
            responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
            return new ResponseEntity<>(responseMap, status);
        }

        User createUser = userService.createUser(params);

        if(!params.userNm.equals(createUser.userNm)){
            message ="정상적으로 생성이 되지 않았습니다.";
            code = "bad request";
            status = HttpStatus.BAD_REQUEST;
        }else{
            message = "사용자가 생성이 되었습니다.";
            code = "ok";
            status = HttpStatus.CREATED;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable Long id,
        @Validated(ValidationGroups.UserUpdateGroup.class) @RequestBody User params, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        User result = userService.updateUser(params, id);

        String message = "사용자 정보가 수정이 되었습니다.";
        String code = "ok";

        if(!params.id.equals(result.id)){
            message ="정상적으로 수정이 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        Boolean result = userService.deleteUser(id);
        String message = "사용자가 삭제 되었습니다.";
        String code = "ok";
        if(result){
            message ="정상적으로 삭제 되지 않았습니다.";
            code = "fail";
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
