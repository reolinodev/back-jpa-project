package com.back.service;

import com.back.domain.Auth;
import com.back.domain.dto.AuthDto;
import com.back.domain.params.AuthParam;
import com.back.repository.AuthCustomRepository;
import com.back.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final AuthCustomRepository authCustomRepository;

    /**
     * 존재하는 권한코드가 있는지 체크합니다.
     */
    public int checkAuthCd(AuthParam authParam) {
        return authRepository.countByAuthCd(authParam.authCd);
    }

    /**
     * 권한을 생성합니다.
     */
    public Auth createAuth(AuthParam authParam) {
        Auth auth  = new Auth();
        auth.setCreateParam(authParam);
        return authRepository.save(auth);
    }

    /**
     * 권한을 전체조회 합니다. 페이징처리
     */
    public Page<AuthDto> getAuths(AuthParam authParam) {
        authParam.setPaging(authParam.page);
        return authCustomRepository.findAllWithPaging(authParam, PageRequest.of(authParam.page, authParam.size));

    }

    /**
     * 권한을 상세 조회 합니다.
     */
    public AuthDto getAuth(Long id) {
        return authCustomRepository.findAuthBy(id);
    }
    
    /**
     * 권한을 수정합니다.
     */
    public Auth updateAuth(Long id, AuthParam authParam){
        Auth auth = authRepository.findById(id).orElseThrow(RuntimeException::new);
        auth.setUpdateParam(authParam);
        return authRepository.save(auth);
    }

    /**
     * 권한을 삭제합니다. ( 사용여부를 N으로 변경 )
     */
    public Auth deleteAuth(Long id, Long updatedId) {
        Auth auth = authRepository.findById(id).orElseThrow(RuntimeException::new);
        auth.useYn = "N";
        auth.updatedId = updatedId;
        return authRepository.save(auth);
    }
}
