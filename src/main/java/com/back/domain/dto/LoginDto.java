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

    public String userNm;

    public String telNo;

    public String userPw;

    public String lastLoginLabel;

    public String loginDevice;

    public String deviceBrowser;

    public String accessToken;

    public String refreshToken;

}
