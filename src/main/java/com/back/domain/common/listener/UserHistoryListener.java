package com.back.domain.common.listener;

import com.back.domain.sample.User;
import com.back.domain.sample.UserHistory;
import com.back.repository.sample.UserHistoryRepository;
import com.back.support.BeanUtils;
import javax.persistence.PostUpdate;

//todo 토큰을 통해 정보 처리
public class UserHistoryListener {

    @PostUpdate
    public void postUpdate(Object o){
        User user = (User) o;

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = new UserHistory();
//        userHistory.userId = 2L;
//        userHistory.callUrl = "user/update";

        userHistoryRepository.save(userHistory);
    }
}
