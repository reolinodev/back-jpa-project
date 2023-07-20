package com.back.controller.sample;

import com.back.domain.common.Header;
import com.back.domain.common.ValidationGroups;
import com.back.domain.sample.User;
import com.back.domain.sample.dto.UserDto;
import com.back.domain.sample.dto.statics.LastLoginHistoryIF;
import com.back.domain.sample.params.UserParam;
import com.back.service.sample.LoginHistoryService;
import com.back.service.sample.UserService;
import com.back.support.ResponseUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sample/statics")
public class StaticsControllerAPI {

    private final LoginHistoryService loginHistoryService;


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


}
