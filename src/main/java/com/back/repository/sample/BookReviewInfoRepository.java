package com.back.repository.sample;

import com.back.domain.sample.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {

    BookReviewInfo findByBookId(Long bookId);

}
