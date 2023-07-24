package com.back.repository;

import com.back.domain.LoginHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginHistoryRepository extends JpaRepository <LoginHistory, Long> {

}

