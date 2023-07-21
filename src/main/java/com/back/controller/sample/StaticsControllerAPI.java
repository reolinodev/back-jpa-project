package com.back.controller.sample;

import com.back.domain.common.Header;
import com.back.domain.sample.Dept;
import com.back.domain.sample.User;
import com.back.domain.sample.dto.statics.LastLoginHistoryIF;
import com.back.service.sample.DeptService;
import com.back.service.sample.LoginHistoryService;
import com.back.support.ResponseUtils;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sample/statics")
public class StaticsControllerAPI {

    private final LoginHistoryService loginHistoryService;

    private final DeptService deptService;


    @GetMapping("/last-login-hisotories")
    public ResponseEntity <Map<String,Object>> getLastLoginHistories(HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        List<LastLoginHistoryIF> results = loginHistoryService.getLastLoginHistories();

        String message = results.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", results);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    @GetMapping("/users-in-dept/{id}")
    public ResponseEntity <Map<String,Object>> getUsersInDept(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        Dept result = deptService.getUsersInDept(id);
        List<User> users = result.users;

        ArrayList<LinkedHashMap<String,Object>> list = new ArrayList<>();

        for(User user : users){
            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
            map.put("userId", user.id);
            map.put("userNm", user.userNm);
            map.put("loginId", user.loginId);
            map.put("telNo", user.telNo);
            map.put("useYn", user.useYn);
            list.add(map);
        }

        String message = list.size()+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", list);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


}
