package com.back.repository.sample;

import com.back.domain.sample.BookReviewInfo;
import com.back.domain.sample.Review;
import com.back.domain.sample.User;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;


    @Test
    void reviewCrudTest() {
        Review review = new Review();
        review.setBook(bookRepository.findById(2L).orElseThrow(RuntimeException::new));
        review.setUser(userRepository.findById(5L).orElseThrow(RuntimeException::new));
        review.title = "내용없어요125111";
        review.content ="별로였어요12533333.";
        review.score = 3.0f;

        reviewRepository.save(review);

        BookReviewInfo bookReviewInfo = bookReviewInfoRepository.findByBookId(review.getBook().getId());;
        bookReviewInfo.averageReviewScore = 0.0f;
        bookReviewInfo.reviewCount = 1;
        bookReviewInfo.setBook(review.getBook());

        System.out.println(reviewRepository.findAll());

    }

//    @Test
//    @Transactional
//    void reviewCrudTest2() {
//        User user = userRepository.findById(2L).orElseThrow(RuntimeException::new);
//
//        System.out.println("Review : " + user.getReviews());
//        System.out.println("Book L " +user.getReviews().get(0).getBook());
//    }
}
