package com.back.repository.sample;

import com.back.domain.sample.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository <LoginHistory, Long> {

}

