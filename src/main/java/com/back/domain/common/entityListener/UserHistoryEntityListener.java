package com.back.domain.common.entityListener;

import com.back.domain.sample.User;
import com.back.domain.sample.UserHistory;
import com.back.repository.sample.UserHistoryRepository;
import com.back.support.BeanUtils;
import javax.persistence.PreUpdate;

//todo 토큰을 통해 정보 처리
public class UserHistoryEntityListener {

    @PreUpdate
    public void preUpdate(Object o){
        User user = (User) o;

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = new UserHistory();
        userHistory.userId = 2L;
        userHistory.callUrl = "user/update";

        userHistoryRepository.save(userHistory);
    }
}
