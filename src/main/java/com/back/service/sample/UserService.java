package com.back.service.sample;

import com.back.domain.sample.User;
import com.back.domain.sample.dto.UserDto;
import com.back.domain.sample.params.UserParam;
import com.back.repository.sample.DeptRepository;
import com.back.repository.sample.UserCustomRepository;
import com.back.repository.sample.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final DeptRepository deptRepository;

    private final UserCustomRepository userCustomRepository;

    /**
     * 사용자를 전체조회 합니다. 페이징처리
     */
    public Page<UserDto> getUsers(UserParam userParam) {
        userParam.setPaging(userParam.page);
        return userCustomRepository.searchWithPaging(userParam, PageRequest.of(userParam.page, userParam.size));
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
    public User createUser(UserParam userParam) {
        User user = new User();
        user.setUser(userParam);
        if(!"".equals(userParam.deptCd)&&userParam.deptCd != null) {
            user.dept = deptRepository.findOneByDeptCd(userParam.deptCd);
        }
        return userRepository.save(user);
    }


    /**
     * 사용자를 수정합니다.
     */
    public User updateUser(UserParam userParam, Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setUser(userParam);
        if(!"".equals(userParam.deptCd)&&userParam.deptCd != null) {
            user.dept = deptRepository.findOneByDeptCd(userParam.deptCd);
        }

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
