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
        // 추후에 세션 아이디로 받게끔 수정
        User user = (User) o;

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = new UserHistory();
        userHistory.userId = 2L;
        userHistory.action = "update";
        userHistory.menuNm = "user";

        userHistoryRepository.save(userHistory);
    }
}
