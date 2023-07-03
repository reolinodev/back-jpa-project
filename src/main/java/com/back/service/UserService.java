package com.back.service;

import com.back.domain.User;
import com.back.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
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
        params.createdAt = LocalDateTime.now();
        params.updatedAt = LocalDateTime.now();
        return userRepository.save(params);
    }


    /**
     * 사용자를 수정합니다.
     */
    public User updateUser(User params, Long userId) {
        params.userId = userId;
        params.updatedAt = LocalDateTime.now();
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
