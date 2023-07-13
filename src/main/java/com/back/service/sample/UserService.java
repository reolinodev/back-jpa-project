package com.back.service.sample;

import com.back.domain.sample.LoginDto;
import com.back.domain.sample.User;
import com.back.domain.sample.UserDto;
import com.back.domain.sample.UserMapping;
import com.back.repository.sample.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * todo 사용자를 전체조회 합니다. (동적 쿼리 변경)
     */
    public Page<UserMapping> getUsers(UserDto params) {
        params.setPageIdx(params.size, params.page);
        return userRepository.findUsersBy(PageRequest.of(params.page,params.size, Sort.by(Order.desc("id"))));
    }
    /**
     * 사용자를 상세 조회 합니다.
     */
    public UserMapping getUser(long id) {
        return userRepository.findUserById(id);
    }
//
//
    /**
     * 사용자를 생성합니다.
     */
    public User createUser(User params) {
        return userRepository.save(params);
    }


    /**
     * 사용자를 수정합니다.
     */
    public User updateUser(User params, Long id) {
        params.id = id;
        return userRepository.save(params);
    }

    /**
     * 사용자를 삭제 합니다.
     */
    public Boolean deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(RuntimeException::new));
        return userRepository.existsById(id);
    }


    /**
     * 존재하는 아이디 인지 체크합니다.
     */
    public User checkUser(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

}
