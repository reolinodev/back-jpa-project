package com.back.service;


import com.back.domain.LoginHistory;
import com.back.domain.User;
import com.back.domain.dto.LoginDto;
import com.back.domain.params.LoginParam;
import com.back.repository.LoginHistoryRepository;
import com.back.repository.UserCustomRepository;
import com.back.repository.UserRepository;
import com.back.support.CryptUtils;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginHistoryRepository loginHistoryRepository;

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

    public User updateLastLoginDt(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.lastLoginAt = LocalDateTime.now();
        user.loginFailCnt = 0;
        return  userRepository.save(user);
    }

    public void updateLoginFailCnt(LoginParam loginParam) {
        User user = userRepository.findByLoginIdAndUseYn(loginParam.loginId, "Y");
        user.loginFailCnt = user.loginFailCnt + 1;
        userRepository.save(user);
    }

    public LoginHistory saveToken(LoginParam loginParam) {
        LoginHistory loginHistory = new LoginHistory();

        loginHistory.setCreateParam(loginParam);
        loginHistory.user = userRepository.findById(loginParam.userId).orElseThrow(RuntimeException::new);

        return loginHistoryRepository.save(loginHistory);
    }

    public void updateToken(LoginHistory loginHistory) {
        loginHistoryRepository.save(loginHistory);
    }

    public LoginHistory getTokenInfo(LoginParam loginParam) {
        return loginHistoryRepository.findByAccessToken(loginParam.accessToken);
    }
}
