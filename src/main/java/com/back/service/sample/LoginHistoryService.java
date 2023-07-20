package com.back.service.sample;

import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.User;
import com.back.domain.sample.dto.statics.LastLoginHistoryIF;
import com.back.domain.sample.params.LoginParam;
import com.back.repository.sample.LoginHistoryRepository;
import com.back.repository.sample.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    /**
     * 가장 최근 로그인 내역 가져오기
     */
    public List<LastLoginHistoryIF> getLastLoginHistories() {
        return loginHistoryRepository.getLastLoginHistories();
    }
}
