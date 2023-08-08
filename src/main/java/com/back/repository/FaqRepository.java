package com.back.repository;

import com.back.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository <Faq, Long> {

}

