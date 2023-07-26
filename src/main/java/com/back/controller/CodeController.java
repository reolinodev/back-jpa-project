package com.back.controller;


import com.back.domain.dto.CodeDto;
import com.back.domain.params.CodeParam;
import com.back.service.CodeService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;
    private final JwtUtils jwtUtils;

   //코드의 목록울 전체 조회한다.
    @GetMapping("/{codeGrpId}")
    public ResponseEntity<Map<String,Object>> getCodes(@PathVariable Long codeGrpId, HttpServletRequest httpServletRequest){
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        List<CodeDto> getCodesResult = codeService.getCodes(codeGrpId);

        String message = getCodesResult.size()+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getCodesResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    @PutMapping("/{codeGrpId}")
    public ResponseEntity<Map<String,Object>> saveCodes(@PathVariable Long codeGrpId,
        @RequestBody CodeParam codeParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        codeParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        codeParam.codeGrpId = codeGrpId;

        codeService.saveCode(codeParam);

        String message = "코드가 저장되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }
}
