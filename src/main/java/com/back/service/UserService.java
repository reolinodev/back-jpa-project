package com.back.service;

import com.back.domain.User;
import com.back.domain.dto.UserDto;
import com.back.domain.params.UserParam;
import com.back.repository.UserCustomRepository;
import com.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserCustomRepository userCustomRepository;

    /**
     * 사용자를 전체조회 합니다. 페이징처리
     */
    public Page<UserDto> getUsers(UserParam userParam) {
        userParam.setPaging(userParam.page);
        return userCustomRepository.findAllWithPaging(userParam, PageRequest.of(userParam.page, userParam.size));
    }
    /**
     * 사용자를 상세 조회 합니다.
     */
    public UserDto getUser(long id) {
        return userCustomRepository.findUserBy(id);
    }

    /**
     * 존재하는 아이디 인지 체크합니다.
     */
    public int checkLoginId(String loginId) {
        return userRepository.countByLoginIdAndUseYn(loginId, "Y");
    }


    /**
     * 사용자를 생성합니다.
     */
    public User createUser(UserParam userParam) throws Exception {
        User user = new User();
        user.setCreateParam(userParam);
        user.pwInitYn = "N";
        user.useYn = "Y";
        return userRepository.save(user);
    }


    /**
     * 사용자를 수정합니다.
     */
    public User updateUser(UserParam userParam, Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setUpdateParam(userParam);
        return userRepository.save(user);
    }

    /**
     * 사용자를 삭제합니다. ( 사용여부를 N으로 변경 )
     */
    public User deleteUser(Long id, Long updatedId)  {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.useYn = "N";
        user.updatedId = updatedId;
        return userRepository.save(user);
    }


    /**
     * 사용자 페이지에서 패스워드를 업데이트 합니다. 초기화 여부를 Y로, 로그인 실패건수를 0으로 업데이트 합니다.
     */
    public User updateUserPw(UserParam userParam, Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        user.setUpdateParam(userParam);
        user.pwInitYn = "Y";
        user.loginFailCnt = 0;

        return userRepository.save(user);
    }


    /**
     * 사용자의 패스워드를 자신의 아이디로 변경합니다. (관리자 페이지에서 업데이트)
     */
    public User initUserPw(Long id, Long updatedId) throws Exception {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        UserParam userParam = new UserParam();
        userParam.userPw = user.loginId;

        user.setUpdateParam(userParam);
        user.updatedId = updatedId;
        user.pwInitYn = "N";
        user.loginFailCnt = 0;

        return userRepository.save(user);
    }

    /**
     * 사용자의 실패 횟수를 0으로 초기화 한다.
     */
    public User initLoginFailCnt(Long id, Long updatedId) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.updatedId = updatedId;
        user.loginFailCnt = 0;

        return userRepository.save(user);
    }

    /**
     * 존재하는 아이디 인지 체크합니다.
     */
    public UserDto getUserByLoginId(String loginId) {
        return userCustomRepository.findUserByLoginId(loginId);
    }
}
