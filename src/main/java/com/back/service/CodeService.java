package com.back.service;

import com.back.domain.Code;
import com.back.domain.dto.CodeDto;
import com.back.domain.params.CodeParam;
import com.back.repository.CodeCustomRepository;
import com.back.repository.CodeGrpRepository;
import com.back.repository.CodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    private final CodeCustomRepository codeCustomRepository;

    private final CodeGrpRepository codeGrpRepository;

    public List<CodeDto> getCodes(Long codeGrpId) {
        return codeCustomRepository.findCodeBy(codeGrpId);
    }

    public void saveCode(CodeParam codeParam) {
        Code[] createdRows = codeParam.createdRows;
        Code[] updatedRows = codeParam.updatedRows;
        Code[] deletedRows = codeParam.deletedRows;

        for (Code createCode : createdRows ) {
            createCode.createdId = codeParam.createdId;
            createCode.codeGrp = codeGrpRepository.findById(codeParam.codeGrpId).orElseThrow(RuntimeException::new);
            codeRepository.save(createCode);
        }

        for (Code updateCode : updatedRows ) {
            Code code = codeRepository.findById(updateCode.id).orElseThrow(RuntimeException::new);
            code.updatedId = codeParam.createdId;
            code.codeNm = updateCode.codeNm;
            code.codeVal = updateCode.codeVal;
            code.prnCodeVal = updateCode.prnCodeVal;
            code.ord = updateCode.ord;
            code.memo = updateCode.memo;
            code.useYn = updateCode.useYn;

            codeRepository.save(code);
        }

        for (Code deleteCode : deletedRows ) {
            codeRepository.deleteById(deleteCode.id);
        }
    }
}
