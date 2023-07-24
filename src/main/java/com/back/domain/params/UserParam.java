package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.LoginGroup;
import com.back.domain.common.ValidationGroups.UserCreateGroup;
import com.back.domain.common.ValidationGroups.UserPwUpdateGroup;
import com.back.domain.common.ValidationGroups.UserUpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
@EqualsAndHashCode(callSuper = true)
public class UserParam extends Page {

    @NotBlank(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "이름을 입력해주세요.")
    @Size(groups = { UserCreateGroup.class, UserUpdateGroup.class}, min = 2, max = 10, message = "최소 2자에서 10자사이로 입력해주세요")
    public String userNm;

    @NotBlank(groups = { UserCreateGroup.class, LoginGroup.class, UserPwUpdateGroup.class}, message = "아이디를 입력해주세요.")
    @Email(groups = { UserCreateGroup.class}, message = "아이디는 이메일 형식 입니다.")
    public String loginId;

    @NotBlank(groups = { LoginGroup.class, UserPwUpdateGroup.class}, message = "비밀번호를 입력해주세요.")
    @Size(groups = { UserPwUpdateGroup.class}, max = 20, message = "최대 20자를 넘길수 없습니다,")
//    @Pattern(groups = { UserPwUpdateGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    public String userPw;

    @Pattern(groups = { UserCreateGroup.class, UserUpdateGroup.class}, regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "올바른 휴대폰번호 형식이 아닙니다. ex) 01011112222")
    public String telNo;

    @NotBlank(groups = { UserUpdateGroup.class}, message = "사용여부를 입력해주세요.")
    public String useYn;

}
