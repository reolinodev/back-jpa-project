package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class LoginDto {
    public Long userId;

    public String loginId;

    public Long authId;

    public String authNm;

    public String authRole;

    public String userNm;

    public String telNo;

    public String pwInitYn;

    public int loginFailCnt;

    public String lastLoginLabel;
}
