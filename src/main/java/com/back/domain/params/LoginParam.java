package com.back.domain.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    //디바이스 브라우저
    public Boolean loginFail;

    //권한식별키
//    public String auth_id;

    //데이터유형
//    public String data_type;

}
