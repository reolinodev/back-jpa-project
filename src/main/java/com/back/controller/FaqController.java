package com.back.controller;

import com.back.domain.Faq;
import com.back.domain.Post;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.FaqDto;
import com.back.domain.params.FaqParam;
import com.back.service.FaqService;
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
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;
    private final JwtUtils jwtUtils;

    //FAQ를 전체 조회한다.
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getFaqs(@RequestBody FaqParam faqParam, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();
        Page<FaqDto> getFaqsResult = faqService.getFaqs(faqParam);
        Long totalCount = getFaqsResult.getTotalElements();
        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getFaqsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }


    //FAQ를 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createFaq(@Validated(ValidationGroups.FaqCreateGroup.class) @RequestBody FaqParam faqParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        String message = "FAQ가 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        faqParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        Faq createFaqResult = faqService.createFaq(faqParam);

        if(!createFaqResult.faqTitle.equals(faqParam.faqTitle)){
            message ="FAQ가 생성에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //FAQ를 상세조회한다.
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getFaq(@PathVariable long id, HttpServletRequest httpServletRequest) {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        FaqDto getFaqResult = faqService.getFaq(id);

        int count = 0;
        if (getFaqResult != null) count= 1;

        String message = count+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getFaqResult);
        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //FAQ를 수정한다.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateFaq(@PathVariable Long id,
        @Validated(ValidationGroups.FaqUpdateGroup.class) @RequestBody FaqParam faqParam, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        faqParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        Faq updateFaqResult = faqService.updateFaq(id, faqParam);

        String message = "FAQ가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(!updateFaqResult.updatedId.equals(faqParam.updatedId)){
            message ="FAQ가 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }
}
