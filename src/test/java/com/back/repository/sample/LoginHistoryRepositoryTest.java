package com.back.repository.sample;

import com.back.domain.sample.dto.statics.LastLoginHistoryIF;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginHistoryRepositoryTest {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Test
    void loginHistoryTest() {
        List<LastLoginHistoryIF> result = loginHistoryRepository.getLastLoginHistories();

        result.forEach(System.out::println);
    }

}
