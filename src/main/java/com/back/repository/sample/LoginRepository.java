package com.back.repository.sample;

import com.back.domain.sample.LoginHistory;
import com.back.domain.sample.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository <LoginHistory, Long> {

}

