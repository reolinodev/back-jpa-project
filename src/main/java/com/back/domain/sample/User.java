package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.common.ValidationGroups.UserCreateGroup;
import com.back.domain.common.ValidationGroups.UserUpdateGroup;
import com.back.domain.common.listener.Auditable;
import com.back.domain.common.listener.UserHistoryListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners({UserHistoryListener.class})
@Table(name="TB_USER", schema = "sample")
public class User extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "이름은 필수 입력값입니다.")
    @Size(groups = { UserCreateGroup.class, UserUpdateGroup.class}, min = 2, max = 10, message = "최소 2자에서 10자사이로 입력해주세요")
    public String userNm;

    @NotBlank(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "이메일은 필수 입력 값입니다.")
    @Email(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "이메일 형식이 올바르지 않습니다.")
    public String email;

    public String birth;

    @NotBlank(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "비밀번호는 필수 입력 값입니다.")
    @Size(groups = { UserCreateGroup.class, UserUpdateGroup.class}, max = 20, message = "최대 20자를 넘길수 없습니다,")
    @Pattern(groups = { UserCreateGroup.class, UserUpdateGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @ToString.Exclude
    public String userPw;

    @NotBlank(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "휴대폰 번호는 필수 입력 값입니다.")
    @Pattern(groups = { UserCreateGroup.class, UserUpdateGroup.class}, regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "올바른 휴대폰번호 형식이 아닙니다. ex) 01011112222")
    public String telNo;

    public String useYn;

    public String deptCd;

//    @OneToOne
//    private Dept dept;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<UserHistory> userHistories = new ArrayList<>();
}
