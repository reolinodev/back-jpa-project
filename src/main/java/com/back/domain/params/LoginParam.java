package com.back.domain.params;

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
public class LoginParam {

    //아이디
    public String loginId;

    //패스워드
    public String userPw;

    //억세스토큰
    public String accessToken;

    //리프레시토큰
    public String refreshToken;

    //접속디바이스
    public String loginDevice;

    //디바이스 브라우저
    public String deviceBrowser;

    //사용자 아이디
    public long userId;

    //권한구분
    public String authRole;



}
