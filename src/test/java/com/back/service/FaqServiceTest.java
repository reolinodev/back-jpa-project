package com.back.service;

import com.back.domain.Faq;
import com.back.domain.dto.FaqDto;
import com.back.domain.params.FaqParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class FaqServiceTest {

    @Autowired
    private FaqService faqService;

    @Test
    void createFaq() {

        //given
        FaqParam faqParam = new FaqParam();
        faqParam.faqTitle ="faq 첫번째글입니다.";
        faqParam.mainText ="faq 본문입니다.";
        faqParam.boardId = 2L;

        //when
        Faq createFaqResult  = faqService.createFaq(faqParam);
        System.out.println("result = " + createFaqResult);

        //then
        Assertions.assertEquals("faq 첫번째글입니다.", createFaqResult.faqTitle);
    }


    @Test
    void getFaqs() {
        //given
        FaqParam faqParam = new FaqParam();
        faqParam.useYn ="Y";
        faqParam.faqTitle = "첫번째글입니다";
        faqParam.size = 10;
        faqParam.page = 1;
        faqParam.boardId = 2L;

        //when
        Page<FaqDto> getFaqsResult  = faqService.getFaqs(faqParam);
        System.out.println("result = " + getFaqsResult);

        //then
        Assertions.assertEquals(1, getFaqsResult.getTotalElements());
    }

    @Test
    void getFaq() {
        //given
        Long id = 1L;

        //when
        FaqDto getFaqResult  = faqService.getFaq(id);
        System.out.println("result = " + getFaqResult);

        //then
        Assertions.assertEquals("faq 첫번째글입니다.", getFaqResult.faqTitle);
    }


    @Test
    void updateFaq() {
        //given
        FaqParam faqParam = new FaqParam();
        faqParam.faqTitle ="faq 첫번째글입니다.1";
        faqParam.mainText ="faq 본문입니다.1";
        faqParam.updatedId = 1L;
        faqParam.useYn = "N";

        //when
        Faq updateFaqResult  = faqService.updateFaq(1L, faqParam);
        System.out.println("result = " + updateFaqResult);

        //then
        Assertions.assertEquals("faq 첫번째글입니다.1", updateFaqResult.faqTitle);
    }

}
