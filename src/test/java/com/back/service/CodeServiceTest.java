package com.back.service;

import com.back.domain.Code;
import com.back.domain.params.CodeParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeServiceTest {

    @Autowired
    private CodeService codeService;

    @Test
    public void  saveCode() {
        //given
        CodeParam codeParam = new CodeParam();
        codeParam.codeGrpId = 3L;
        codeParam.createdId = 3L;

        Code[] createdRows = new Code[1];
        Code[] updatedRows = new Code[0];
        Code[] deletedRows =new Code[0];

        Code createCode1 = new Code();
        createCode1.codeNm = "웹";
        createCode1.codeVal = "WEB";
        createCode1.memo ="웹의 권한";
        createCode1.ord ="1";
        createCode1.useYn = "Y";

//        Code createCode2 = new Code();
//        createCode2.codeNm = "60개";
//        createCode2.codeVal = "60";
//        createCode2.memo ="페이지당 60개";
//        createCode2.ord ="5";
//        createCode2.useYn = "Y";
//
//        Code updateCode1 = new Code();
//        updateCode1.id = 5L;
//        updateCode1.codeNm = "30개";
//        updateCode1.codeVal = "30";
//        updateCode1.memo ="페이지당 30개";
//        updateCode1.ord ="3";
//        updateCode1.useYn = "N";
//
//        Code deleteCode1 = new Code();
//        deleteCode1.id = 4L;
//        deleteCode1.codeNm = "20개";
//        deleteCode1.codeVal = "20";
//        deleteCode1.memo ="페이지당 20개";
//        deleteCode1.ord ="2";
//        deleteCode1.useYn = "Y";

        createdRows[0] = createCode1;
//        createdRows[1] = createCode2;
//        updatedRows[0] = updateCode1;
//        deletedRows[0] = deleteCode1;

        codeParam.createdRows = createdRows;
        codeParam.updatedRows = updatedRows;
        codeParam.deletedRows = deletedRows;

        //when
        codeService.saveCode(codeParam);
    }

    @Test
    public void  getCodeList() {
        Long codeGrpId = 2L;
        var result = codeService.getCodes(codeGrpId);
        System.out.println("<<"+result);

    }
}
