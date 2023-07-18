package com.back.service.sample;

import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.User;
import com.back.domain.sample.params.LoginParam;
import com.back.repository.sample.LoginHistoryRepository;
import com.back.repository.sample.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private final LoginHistoryRepository loginHistoryRepository;


    /**
     * 아이디가 존재하는지 체크
     */
    public User checkUser(LoginParam loginParam) {
        return userRepository.findByLoginIdAndUseYn(loginParam.loginId, "Y");
    }

    /**
     * 사용자 정보를 가져온다.
     */
    public int checkUserPw(LoginParam loginParam) {
        return userRepository.countByLoginIdAndUserPw(loginParam.loginId, loginParam.userPw);
    }

    /**
     * 로그인 이력을 등록한다.
     */
    public LoginHistory createLoginHistory(LoginHistory loginHistory) {
        return loginHistoryRepository.save(loginHistory);
    }

    /**
     * 로그인 실패 횟수를 변경한다.
     */
    public User updateLoginFailCnt(User params, Boolean chkLogin) {
        if(!chkLogin){
            params.loginFailCnt = params.loginFailCnt + 1;
        }else {
            params.loginFailCnt = 0;
        }
        return userRepository.save(params);
    }

    /**
     * 사용자의 패스워드를 변경한다.
     */
    public User updateUserPw(LoginParam loginParam) {
        User user = userRepository.findByLoginId(loginParam.loginId);
        user.userPw = loginParam.userPw;
        user.useYn = "N";
        user.loginFailCnt = 0;
        return userRepository.save(user);
    }

}
