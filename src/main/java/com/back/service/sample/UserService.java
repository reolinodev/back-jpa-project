package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.repository.sample.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자를 전체조회 합니다.
     */
//    public List<User> getUsersList(User params) {
////        return userRepository.findAll(params);
//    }

    /**
     * 사용자를 상세 조회 합니다.
     */
    public User getUserData(long id) {
        return userRepository.findByUserId(id);
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
    public User updateUser(User params, Long userId) {
        params.userId = userId;
        return userRepository.save(params);
    }

    /**
     * 사용자를 삭제 합니다.
     */
    public Boolean deleteUser(Long userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(RuntimeException::new));
        return userRepository.existsById(userId);

    }
}
