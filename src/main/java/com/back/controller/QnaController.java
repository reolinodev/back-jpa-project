package com.back.controller;

import com.back.domain.Qna;
import com.back.domain.common.Header;
import com.back.domain.common.ValidationGroups;
import com.back.domain.dto.QnaDto;
import com.back.domain.params.QnaParam;
import com.back.service.QnaService;
import com.back.support.ResponseUtils;
import com.back.token.JwtUtils;
import java.util.HashMap;
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
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;
    private final JwtUtils jwtUtils;

    //QNA를 전체조회한다
    @PostMapping("")
    public ResponseEntity<Map<String,Object>> getQnas(@RequestBody QnaParam qnaParam, HttpServletRequest httpServletRequest) {
        Map <String,Object> responseMap = new HashMap<>();

        Page<QnaDto> getPostsResult = qnaService.getQnas(qnaParam);
        Long totalCount = getPostsResult.getTotalElements();

        String message = totalCount+"건이 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getPostsResult.getContent());
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<> (responseMap, HttpStatus.OK);
    }

    //QNA글을 입력한다.
    @PutMapping("")
    public ResponseEntity<Map<String,Object>> createQna(
        @Validated(ValidationGroups.QnaCreateGroup.class) @RequestBody QnaParam qnaParam, HttpServletRequest httpServletRequest)
        throws Exception {

        LinkedHashMap<String,Object> responseMap = new LinkedHashMap<>();

        String message = "QNA가 생성되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.CREATED;

        qnaParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        Qna createQnaResult = qnaService.createQna(qnaParam);

        if(!createQnaResult.qnaTitle.equals(qnaParam.qnaTitle)){
            message ="QNA 생성이 실패되었습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }

    //QNA글을 상세조회한다.
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getQna(@PathVariable Long id, HttpServletRequest httpServletRequest) {

        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        QnaDto getQnaReulst = qnaService.getQna(id);
        int count = 0;
        if (getQnaReulst != null) count= 1;

        String message = count+" 개가 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", getQnaReulst);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }

    //QNA글이 비밀글일 경우 패스워드를 체크한다.
    @PostMapping("/qna-pw/{id}")
    public ResponseEntity<Map<String,Object>> getQna(@PathVariable Long id,
        @RequestBody QnaParam qnaParam, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        qnaParam.createdId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        boolean checkQnaPwResult = qnaService.checkQnaPw(id, qnaParam);

        String message = "정상적으로 조회되었습니다.";
        String code = "ok";

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));
        responseMap.put("data", checkQnaPwResult);

        return new ResponseEntity<> (responseMap,  HttpStatus.OK);
    }

    //QNA글을 수정한다.(사용자가 자신의 글을 수정할 경우)
    @PutMapping("/user/{id}")
    public ResponseEntity<Map<String,Object>> updateUserQna(@PathVariable Long id,
        @Validated(ValidationGroups.QnaUserUpdateGroup.class) @RequestBody QnaParam qnaParam, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        qnaParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        Qna updateQnaResult = qnaService.updateQna(id, qnaParam);

        String message = "QNA가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(!updateQnaResult.updatedId.equals(qnaParam.updatedId)){
            message ="QNA 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //QNA글을 수정한다.(관리자가 수정하는 경우, 답변)
    @PutMapping("/admin/{id}")
    public ResponseEntity<Map<String,Object>> updateAdminQna(@PathVariable Long id,
        @Validated(ValidationGroups.QnaAdminUpdateGroup.class) @RequestBody QnaParam qnaParam, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        qnaParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));

        Qna updateQnaResult = qnaService.updateQna(id, qnaParam);

        String message = "QNA가 수정되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(!updateQnaResult.updatedId.equals(qnaParam.updatedId)){
            message ="QNA 수정에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        responseMap.put("header", ResponseUtils.setHeader(message, code, httpServletRequest));

        return new ResponseEntity<>(responseMap, status);
    }

    //QNA글을 삭제한다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteQna(@PathVariable Long id, HttpServletRequest httpServletRequest) throws Exception {
        LinkedHashMap <String,Object> responseMap = new LinkedHashMap<>();

        QnaParam qnaParam = new QnaParam();
        qnaParam.updatedId = jwtUtils.getTokenUserId(jwtUtils.resolveToken(httpServletRequest));
        qnaParam.useYn = "N";

        Qna updateQnaResult = qnaService.updateQna(id, qnaParam);

        String message = "QNA가 삭제되었습니다.";
        String code = "ok";
        HttpStatus status = HttpStatus.OK;

        if(!updateQnaResult.updatedId.equals(qnaParam.updatedId)){
            message ="QNA 삭제에 실패하였습니다.";
            code = "fail";
            status = HttpStatus.BAD_REQUEST;
        }

        Header header = ResponseUtils.setHeader(message, code, httpServletRequest);
        responseMap.put("header", header);

        return new ResponseEntity<>(responseMap, status);
    }
}
