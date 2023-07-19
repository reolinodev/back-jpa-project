package com.back.repository.sample;

import com.back.domain.sample.Code;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeRepositoryTest {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeGrpRepository codeGrpRepository;

    @Test
    void saveAll() {
        Code code = new Code();
        code.codeNm = "예";
        code.codeValue = "Y";
        code.codeDetail = "사용여부 예";
        code.useYn = "Y";
        code.codeGrp = codeGrpRepository.findById(1L).orElseThrow(RuntimeException::new);

        Code code2 = new Code();
        code2.codeNm = "아니오";
        code2.codeValue = "N";
        code2.codeDetail = "사용여부 아니오";
        code2.useYn = "Y";
        code2.codeGrp = codeGrpRepository.findById(1L).orElseThrow(RuntimeException::new);

        List<Code> result = codeRepository.saveAll(Lists.newArrayList(code, code2));

        result.forEach(System.out::println);
    }



    @Test
    void saveAll2() {
        Code code = new Code();
        code.codeNm = "일반소설";
        code.codeValue = "100";
        code.codeDetail = "";
        code.useYn = "Y";
        code.codeGrp = codeGrpRepository.findById(2L).orElseThrow(RuntimeException::new);

        Code code2 = new Code();
        code2.codeNm = "역사";
        code2.codeValue = "200";
        code2.codeDetail = "";
        code2.useYn = "Y";
        code2.codeGrp = codeGrpRepository.findById(2L).orElseThrow(RuntimeException::new);

        Code code3 = new Code();
        code3.codeNm = "비문학";
        code3.codeValue = "300";
        code3.codeDetail = "";
        code3.useYn = "Y";
        code3.codeGrp = codeGrpRepository.findById(2L).orElseThrow(RuntimeException::new);

        List<Code> result = codeRepository.saveAll(Lists.newArrayList(code, code2, code3));

        result.forEach(System.out::println);
    }

}
