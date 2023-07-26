package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.CodeGrpCreateGroup;
import com.back.domain.common.ValidationGroups.CodeGrpUpdateGroup;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class CodeGrpParam extends Page {

    //코드그룹식별키
    public Long codeGrpId;

    //코드그룹명
    @NotEmpty(groups = { CodeGrpCreateGroup.class, CodeGrpUpdateGroup.class }, message = "코드그룹명을 입력해주세요.")
    @Size(groups = { CodeGrpCreateGroup.class, CodeGrpUpdateGroup.class },max=15, message = "15자 이내로 입력해주세요.")
    public String codeGrpNm;

    //코드그룹값
    @NotEmpty(groups = { CodeGrpCreateGroup.class }, message = "코드그룹값을 입력해주세요.")
    @Size(groups = { CodeGrpCreateGroup.class }, max=15, message = "15자 이내로 입력해주세요.")
    public String codeGrpVal;

    //사용여부
    @NotEmpty(groups = { CodeGrpUpdateGroup.class }, message = "사용여부를 입력해주세요.")
    public String useYn;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

}
