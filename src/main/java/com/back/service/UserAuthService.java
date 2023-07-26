package com.back.service;


import com.back.domain.UserAuth;
import com.back.domain.dto.UserAuthDto;
import com.back.domain.dto.UserAuthInputDto;
import com.back.domain.params.UserAuthParam;
import com.back.repository.AuthRepository;
import com.back.repository.UserAuthCustomRepository;
import com.back.repository.UserAuthRepository;
import com.back.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;

    private final UserAuthCustomRepository userAuthCustomRepository;

    private final UserRepository userRepository;

    private final AuthRepository authRepository;

    public Page<UserAuthDto> getUserAuths(UserAuthParam userAuthParam) {
        userAuthParam.setPaging(userAuthParam.page);
        return userAuthCustomRepository.findAllWithPaging(userAuthParam, PageRequest.of(userAuthParam.page, userAuthParam.size));
    }


    public Page<UserAuthInputDto> getInputUserAuths(UserAuthParam userAuthParam) {
        userAuthParam.setPaging(userAuthParam.page);
        return userAuthCustomRepository.findAllInputUserAuthsWithPaging(userAuthParam, PageRequest.of(userAuthParam.page, userAuthParam.size));
    }

    public List<UserAuth> createUserAuths(UserAuthParam userAuthParam) {
        List<UserAuth> list = new ArrayList<>();

        Long[] userArr = userAuthParam.userArr;
        for (Long userId : userArr) {
            UserAuth userAuth = userAuthRepository.findByUserIdAndAuthId(userId, userAuthParam.authId);

            if(userAuth == null){
                userAuth = new UserAuth();
                userAuth.auth = authRepository.findById(userAuthParam.authId).orElseThrow(RuntimeException::new);
                userAuth.user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
                userAuth.useYn = "Y";
                userAuth.createdId = userAuthParam.createdId;
            }else {
                userAuth.useYn = "Y";
                userAuth.updatedId = userAuthParam.createdId;
            }

            list.add(userAuth);
        }

        return  userAuthRepository.saveAll(list);
    }

    public List<UserAuth> deleteUserAuth(UserAuthParam userAuthParam) {
        List<UserAuth> list = new ArrayList<>();

        Long[] userArr = userAuthParam.userArr;
        for (Long userId : userArr) {
            UserAuth userAuth = userAuthRepository.findByUserIdAndAuthId(userId, userAuthParam.authId);

            if(userAuth != null){
                userAuth.useYn = "N";
                userAuth.updatedId = userAuthParam.updatedId;
            }

            list.add(userAuth);
        }

        return  userAuthRepository.saveAll(list);
    }
}
