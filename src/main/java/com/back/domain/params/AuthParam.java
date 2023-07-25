package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.AuthCreateGroup;
import com.back.domain.common.ValidationGroups.AuthUpdateGroup;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
//권한정보
public class AuthParam extends Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //권한명
    @NotEmpty(groups = {AuthCreateGroup.class, AuthUpdateGroup.class}, message = "권한명을 입력해 주세요.")
    @Size(groups = {AuthCreateGroup.class, AuthUpdateGroup.class}, max = 15, message = "최대 15자까지 입력해주세요.")
    public String authNm;

    //권한코드
    @NotEmpty(groups = {AuthCreateGroup.class}, message = "권한코드를 입력해 주세요.")
    @Size(groups = {AuthCreateGroup.class}, max = 15, message = "최대 15자까지 입력해주세요.")
    public String authCd;

    //권한구분
    @NotEmpty(groups = {AuthCreateGroup.class}, message = "권한구분을 입력해 주세요.")
    public String authRole;

    //순서
    public String ord;

    //비고
    @Size(groups = {AuthCreateGroup.class}, max = 150, message = "최대 150자까지 입력해주세요.")
    public String memo;

    @NotBlank(groups = { AuthUpdateGroup.class, AuthUpdateGroup.class}, message = "사용여부는 필수 입력 값입니다.")
    //사용여부
    public String useYn;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

}
