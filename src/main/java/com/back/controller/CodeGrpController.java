package com.back.controller;

import com.back.domain.CodeGrp;
import com.back.domain.common.Header;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.CodeGrpDto;
import com.back.domain.params.CodeGrpParam;
import com.back.service.CodeGrpService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/codeGrp")
@RequiredArgsConstructor
public class CodeGrpController {

    private final CodeGrpService codeGrpService;
    private final JwtUtils jwtUtils;

    //코드그룹 리스트를 전체 조회한다.
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getCodeGrps(
        @RequestBody CodeGrpParam codeGrpParam, HttpServletRequest httpServletRequest){
        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        Page<CodeGrpDto> getCodeGrpsResult = codeGrpService.getCodeGrps(codeGrpParam);
        Long totalCount = getCodeGrpsResult.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getCodeGrpsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //코드그룹을 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> saveCodeGrp(
        @Validated(ValidationGroups.CodeGrpCreateGroup.class) @RequestBody CodeGrpParam codeGrpParam,
        HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        String message = "코드그룹이 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        int count = codeGrpService.checkCodeGrpVal(codeGrpParam);

        if(count > 0){
            message ="중복된 코드그룹값이 존재합니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }else {
            codeGrpParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
            CodeGrp createCodeGrpResult = codeGrpService.createCodeGrp(codeGrpParam);

            if(!codeGrpParam.codeGrpVal.equals(createCodeGrpResult.codeGrpVal)){
                message ="코드그룹 생성에 실패하였습니다.";
                code = "fail";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    //코드그룹을 상세 조회한다.
    @GetMapping("/{id}")
    public ResponseEntity <Map<String,Object>> getCodeGrp(@PathVariable Long id, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        CodeGrpDto getCodeGrpResult = codeGrpService.getCodeGrp(id);
        int count = 0;
        if (getCodeGrpResult != null) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";
        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);

        responseMap.put("header", header);
        responseMap.put("data", getCodeGrpResult);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

   //코드그룹을 수정한다.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateCodeGrp(@PathVariable Long id,
        @Validated(ValidationGroups.CodeGrpUpdateGroup.class) @RequestBody CodeGrpParam codeGrpParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        codeGrpParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        CodeGrp updateCodeGrpResult = codeGrpService.updateCodeGrp(id, codeGrpParam);

        String message = "코드그룹이 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(updateCodeGrpResult.updatedId == null){
            message ="코드그룹 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
