package com.back.repository.sample;

import com.back.domain.sample.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review, Long> {

}

