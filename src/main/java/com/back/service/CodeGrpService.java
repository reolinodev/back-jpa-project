package com.back.service;

import com.back.domain.Auth;
import com.back.domain.CodeGrp;
import com.back.domain.dto.CodeGrpDto;
import com.back.domain.params.AuthParam;
import com.back.domain.params.CodeGrpParam;
import com.back.repository.CodeGrpCustomRepository;
import com.back.repository.CodeGrpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeGrpService {

    private final CodeGrpRepository codeGrpRepository;

    private final CodeGrpCustomRepository codeGrpCustomRepository;


    public Page<CodeGrpDto> getCodeGrps(CodeGrpParam codeGrpParam) {
        codeGrpParam.setPaging(codeGrpParam.page);
        return codeGrpCustomRepository.findAllWithPaging(codeGrpParam, PageRequest.of(codeGrpParam.page, codeGrpParam.size));
    }


    public int checkCodeGrpVal(CodeGrpParam codeGrpParam) {
        return codeGrpRepository.countByCodeGrpVal(codeGrpParam.codeGrpVal);
    }

    public CodeGrp createCodeGrp(CodeGrpParam codeGrpParam) {
        CodeGrp codeGrp = new CodeGrp();
        codeGrp.setCreateParam(codeGrpParam);
        return codeGrpRepository.save(codeGrp);
    }

    public CodeGrpDto getCodeGrp(long id) {
        return codeGrpCustomRepository.findCodeGrpBy(id);
    }

    public CodeGrp updateCodeGrp(Long id, CodeGrpParam codeGrpParam) {
        CodeGrp codeGrp = codeGrpRepository.findById(id).orElseThrow(RuntimeException::new);
        codeGrp.setUpdateParam(codeGrpParam);
        return codeGrpRepository.save(codeGrp);
    }

}
