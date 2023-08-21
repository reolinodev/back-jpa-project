package com.back.service;

import com.back.domain.Qna;
import com.back.domain.dto.QnaDto;
import com.back.domain.params.QnaParam;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class QnaServiceTest {

    @Autowired
    private QnaService qnaService;

    @Test
    void createQna() throws NoSuchAlgorithmException {
        //given
        QnaParam qnaParam = new QnaParam();
        qnaParam.boardId = 5L;
        qnaParam.qnaTitle ="둘째 질문입니다.";
        qnaParam.questions ="둘째 질문입니다.";
        qnaParam.hiddenYn = "Y";
        qnaParam.qnaPw= "1111";
        qnaParam.createdId = 1L;

        //when
        Qna createQnaResult  = qnaService.createQna(qnaParam);
        System.out.println("result = " + createQnaResult);

        //then
        Assertions.assertEquals("첫째 질문입니다.", createQnaResult.qnaTitle);
    }

    @Test
    void getQnas() {
        //given
        QnaParam qnaParam = new QnaParam();
        qnaParam.useYn ="Y";
        qnaParam.qnaTitle = "첫째 질문입니다.";
        qnaParam.size = 10;
        qnaParam.page = 1;
        qnaParam.createdNm = "관리자";
        qnaParam.startDate = "2023-08-08";
        qnaParam.endDate = "2023-08-08";
        qnaParam.boardId = 3L;

        //when
        Page<QnaDto> getQnasResult  = qnaService.getQnas(qnaParam);
        System.out.println("result = " + getQnasResult);

        //then
        Assertions.assertEquals(1, getQnasResult.getTotalElements());
    }


    @Test
    void getQna() {
        //given
        Long id = 1L;

        //when
        QnaDto getQnaResult  = qnaService.getQna(id);
        System.out.println("result = " + getQnaResult);

        //then
        Assertions.assertEquals("둘째 질문입니다.", getQnaResult.qnaTitle);
    }


    @Test
    void updateQna() throws NoSuchAlgorithmException {
        //given
        QnaParam qnaParam = new QnaParam();
        qnaParam.updatedId =2L;
        qnaParam.mainText = "둘째 질문 응답입니다.";
        qnaParam.useYn = "Y";
        qnaParam.hiddenYn = "Y";
        qnaParam.qnaPw = "1111";

        //when
        Qna updateQnaResult  = qnaService.updateQna(1L, qnaParam);
        System.out.println("result = " + updateQnaResult);

        //then
        Assertions.assertEquals("둘째 질문입니다.2", updateQnaResult.qnaTitle);
    }


    @Test
    void initQnaPw()  {
        //given
        QnaParam qnaParam = new QnaParam();
        qnaParam.updatedId = 1L;

        //when
        Qna initQnaPwResult  = qnaService.initQnaPw(3L, qnaParam);
        System.out.println("result = " + initQnaPwResult);

    }
}
