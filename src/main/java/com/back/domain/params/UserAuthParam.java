package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.UserAuthUpdateGroup;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserAuthParam extends Page {

    public Long[] userArr;

    public long authId;

    public String authRole;

    public String authCd;

    public String loginId;

    public String userNm;

    //사용여부
    @NotBlank(groups = { UserAuthUpdateGroup.class}, message = "사용여부를 입력해주세요.")
    public String useYn;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;
}
