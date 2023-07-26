package com.back.domain.params;

import com.back.domain.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class CodeParam {

    //코드그룹아이디
    public Long codeGrpId;

    //생성된 코드 Row
    public Code[] createdRows;

    //수정된 코드 Row
    public Code[] updatedRows;

    //삭제된 코드 Row
    public Code[] deletedRows;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

}
