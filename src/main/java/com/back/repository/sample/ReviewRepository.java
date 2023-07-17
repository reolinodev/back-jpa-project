package com.back.repository.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review, Long> {
    Page<Review> findReviewsBy(Pageable pageable);

    int countByBookAndUseYn(Book book, String useYn);
}

