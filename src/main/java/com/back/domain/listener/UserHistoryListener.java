package com.back.domain.listener;

import com.back.domain.User;
import com.back.domain.UserHistory;
//import com.back.repository.UserHistoryRepository;
import com.back.support.BeanUtils;
import javax.persistence.PostUpdate;

public class UserHistoryListener {

    @PostUpdate
    public void postUpdate(Object o){
        User user = (User) o;

//        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = new UserHistory();
        userHistory.setUser(user);
        userHistory.callUrl = "user update";

//        userHistoryRepository.save(userHistory);
    }
}
