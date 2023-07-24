package com.back.repository;

import com.back.domain.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository <LoginHistory, Long> {

}

