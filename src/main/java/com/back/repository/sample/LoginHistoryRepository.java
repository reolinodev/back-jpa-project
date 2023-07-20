package com.back.repository.sample;

import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.dto.statics.LastLoginHistoryIF;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginHistoryRepository extends JpaRepository <LoginHistory, Long> {

    String getLastLoginHistoriesSql =
          "select created_at as createdAt"
              + ", user_id as userId"
              + ", user_nm as userNm"
              + ", count(user_id) cnt "
        + "from ("
             + "select to_char(a.created_at, 'yyyy-mm-dd') created_at"
                    + ", user_id"
                    + ", b.user_nm "
                + "from sample.tb_login_history a "
                + "inner join sample.tb_user b "
                + "on a.user_id  = b.id "
                + "where a.created_at > now() - interval '14 day' "
              + ") as a "
        + "group by created_at, user_id, user_nm "
        + "order by created_at desc ";

    @Query(value = getLastLoginHistoriesSql, nativeQuery = true)
    List<LastLoginHistoryIF> getLastLoginHistories();
}

