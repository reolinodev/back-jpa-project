package com.back.repository;

import com.back.domain.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository <Qna, Long> {

}

