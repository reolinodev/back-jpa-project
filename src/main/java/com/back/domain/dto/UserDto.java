package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class UserDto {
    public Long userId;

    public String loginId;

    public String userNm;

    public String telNo;

    public String useYn;

    public String useYnLabel;

    public String lastLoginLabel;

    public String pwInitYn;

    public int loginFailCnt;

    public String createdAtLabel;

    public String updatedAtLabel;

    public Long createdId;

    public String createdIdLabel;

    public Long updatedId;

    public String updatedIdLabel;
}
