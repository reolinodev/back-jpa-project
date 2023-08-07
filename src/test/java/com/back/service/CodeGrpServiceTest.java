package com.back.service;

import com.back.domain.CodeGrp;
import com.back.domain.dto.CodeGrpDto;
import com.back.domain.params.CodeGrpParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class CodeGrpServiceTest {

    @Autowired
    private CodeGrpService codeGrpService;

    @Test
    void createCodeGrp() {
        //given
        CodeGrpParam codeGrpParam = new CodeGrpParam();
        codeGrpParam.codeGrpNm ="게시판유형";
        codeGrpParam.codeGrpVal ="BOARD_TYPE";

        //when
        CodeGrp result  = codeGrpService.createCodeGrp(codeGrpParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals("첨부파일여부", result.codeGrpNm);
    }


    @Test
    void checkCodeGrpVal() {
        //given
        CodeGrpParam codeGrpParam = new CodeGrpParam();
        codeGrpParam.codeGrpVal ="USE-YN";

        //when
        int result  = codeGrpService.checkCodeGrpVal(codeGrpParam);
        System.out.println("result = " + result);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void getCodeGrps() {
        //given
        CodeGrpParam codeGrpParam = new CodeGrpParam();
        codeGrpParam.useYn ="Y";

        //when
        Page<CodeGrpDto> getCodeGrpsResult  = codeGrpService.getCodeGrps(codeGrpParam);
        System.out.println("result = " + getCodeGrpsResult);

        //then
        Assertions.assertEquals(2, getCodeGrpsResult.getTotalElements());
    }

    @Test
    void getCodeGrp() {
        //given
        Long id = 2L;

        //when
        CodeGrpDto getCodeGrpResult  = codeGrpService.getCodeGrp(id);
        System.out.println("result = " + getCodeGrpResult);

        //then
        Assertions.assertEquals("페이지 항목수", getCodeGrpResult.codeGrpNm);
    }


    @Test
    void updateCodeGrp() {
        //given
        CodeGrpParam codeGrpParam = new CodeGrpParam();
        codeGrpParam.useYn ="N";
        codeGrpParam.updatedId = 2L;

        Long id = 2L;

        //when
        CodeGrp updateCodeGrpResult  = codeGrpService.updateCodeGrp(id, codeGrpParam);
        System.out.println("result = " + updateCodeGrpResult);

        //then
        Assertions.assertEquals("N", updateCodeGrpResult.useYn);
    }

}
