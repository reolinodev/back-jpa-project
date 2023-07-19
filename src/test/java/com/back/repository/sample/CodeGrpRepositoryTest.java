package com.back.repository.sample;

import com.back.domain.sample.CodeGrp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeGrpRepositoryTest {


    @Autowired
    private CodeGrpRepository codeGrpRepository;

    @Test
    void save() {
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.codeGrpNm = "서적 카테고리";
        codeGrp.codeGrpValue = "BOOK_CATEGORY";
        codeGrp.codeGrpDetail = "서적의 종류";
        codeGrp.useYn = "Y";

        CodeGrp result = codeGrpRepository.save(codeGrp);

        System.out.println("result :" + result);

    }

}
