package com.back.domain.common.listener;

import com.back.domain.sample.BookReviewInfo;
import com.back.domain.sample.Review;
import com.back.domain.sample.User;
import com.back.domain.sample.UserHistory;
import com.back.repository.sample.BookReviewInfoRepository;
import com.back.repository.sample.UserHistoryRepository;
import com.back.support.BeanUtils;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class BookReviewInfoListener {

    @PostPersist
    @PostUpdate
    public void reviewInfoUpdate(Object o){
        Review review = (Review) o;

        BookReviewInfoRepository bookReviewInfoRepository = BeanUtils.getBean(BookReviewInfoRepository.class);

        BookReviewInfo bookReviewInfo = bookReviewInfoRepository.findByBookId(review.getBook().getId());;
        bookReviewInfo.averageReviewScore = 0.0f;
        bookReviewInfo.reviewCount = 1;
        bookReviewInfo.setBook(review.getBook());

        bookReviewInfoRepository.save(bookReviewInfo);
    }
}
