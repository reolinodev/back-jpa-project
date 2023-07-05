package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.repository.sample.UserRepository;
import java.util.Optional;
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
        return userRepository.findById(id).get();
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
}
