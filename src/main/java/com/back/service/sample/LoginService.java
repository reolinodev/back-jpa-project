package com.back.service.sample;

import com.back.domain.sample.Login;
import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.User;
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
    public User checkExistUser(Login params) {
        return userRepository.findByLoginIdAndUseYn(params.loginId, "Y");
    }

    /**
     * 사용자 정보를 가져온다.
     */
    public int checkUserPw(Login params) {
        return userRepository.countByLoginIdAndUserPw(params.loginId, params.userPw);
    }

    /**
     * 로그인 이력을 등록한다.
     */
    public LoginHistory createLoginHistory(LoginHistory params) {
        return loginHistoryRepository.save(params);
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
    public User updateUserPw(Login params, User userData) {
        userData.setUserPw(params.userPw);
        userData.loginFailCnt = 0;
        return userRepository.save(userData);
    }

}
