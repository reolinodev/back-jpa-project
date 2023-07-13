package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.UserDto;
import com.back.repository.sample.UserRepository;
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
    public Page<User> getUsers(UserDto userDto) {
        userDto.setPageIdx(userDto.page);
        return userRepository.findUsersBy(PageRequest.of(userDto.page,userDto.size, Sort.by(Order.desc("id"))));
    }
    /**
     * 사용자를 상세 조회 합니다.
     */
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }
//
//
    /**
     * 사용자를 생성합니다.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }


    /**
     * 사용자를 수정합니다.
     */
    public User updateUser(User user, Long id) {
        user.id = id;
        return userRepository.save(user);
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
