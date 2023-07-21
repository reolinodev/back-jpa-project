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
     * 존재하는 아이디 인지 체크합니다.
     */
    public User checkLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

}
