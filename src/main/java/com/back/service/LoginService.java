package com.back.service;


import com.back.domain.User;
import com.back.domain.dto.LoginDto;
import com.back.domain.params.LoginParam;
import com.back.repository.UserCustomRepository;
import com.back.repository.UserRepository;
import com.back.support.CryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

//    private final LoginRepository loginRepository;

    private final UserRepository userRepository;

    private final UserCustomRepository userCustomRepository;

    public int checkLoginId(LoginParam loginParam) {
        return userRepository.countByLoginIdAndUseYn(loginParam.loginId, "Y");
    }

    public int checkUserPw(LoginParam loginParam) throws Exception {
        return userRepository.countByLoginIdAndUserPw(loginParam.loginId, CryptUtils.encryptSha256(loginParam.userPw));
    }

    public LoginDto getLoginUser(String loginId) {
        return userCustomRepository.findLoginUser(loginId);
    }
//
//    public int updateLastLoginDt(LoginEntity loginEntity) {
//        return  loginRepository.saveLastLoginAt(loginEntity);
//    }
//
    public void updateLoginFailCnt(LoginParam loginParam) {
        User user = userRepository.findByLoginIdAndUseYn(loginParam.loginId, "Y");
        user.loginFailCnt = user.loginFailCnt + 1;
        userRepository.save(user);
    }
//
//    public int saveToken(LoginEntity loginEntity) {
//        return loginRepository.saveToken(loginEntity);
//    }
//
//    public LoginEntity getTokenInfo(LoginEntity loginEntity) {
//        return loginRepository.findTokenByAccessToken(loginEntity);
//    }
//
//    public int saveLoginHistory(LoginEntity loginEntity) {
//        return loginRepository.saveLoginHistory(loginEntity);
//    }

}
