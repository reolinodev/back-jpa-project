package com.back.repository.sample;

import com.back.domain.sample.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserHistoryRepositoryTest {

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Test
    void userHistoryTest() {

        UserHistory userHistory = new UserHistory();
        userHistory.userId = 2L;
        userHistory.callUrl = "user/update";

        userHistoryRepository.save(userHistory);

        System.out.println(userHistoryRepository.findAll());
    }

}
