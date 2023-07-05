package com.back.repository.sample;

import com.back.domain.sample.LoginHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginRepositoryTest {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    void loginHistoryTest() {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.userId = 1L;
        loginHistory.device = "PC";

        loginRepository.save(loginHistory);

        System.out.println(loginRepository.findAll());
    }

}
