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

    public String useYnVal;

    public String lastLoginVal;

    public String pwInitYn;

    public int loginFailCnt;

    public String createdAtVal;

    public String updatedAtVal;

    public Long createdId;

    public String createdIdVal;

    public Long updatedId;

    public String updatedIdVal;
}
