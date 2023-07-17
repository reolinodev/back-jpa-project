package com.back.service.sample;

import com.back.domain.sample.Review;
import com.back.domain.sample.ReviewDto;
import com.back.domain.sample.User;
import com.back.domain.sample.UserDto;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    void createReview() {
        //given
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.userId = 5L;
        reviewDto.bookId = 2L;
        reviewDto.score = 5.0f;
        reviewDto.title = "난 좋은데..";
        reviewDto.content ="너무 재미있네요.";

        //when
        Review createReviewResult = reviewService.createReview(reviewDto);
        System.out.println("result = " + createReviewResult);

        //then
        Assertions.assertEquals("저는 약간...", createReviewResult.title);
    }

    @Test
    void updateReview() {
        //given
        Review review = new Review();
        review.score = 3.0f;
        review.title = "난 좋은데..11";
        review.content ="너무 재미있네요...11";
        review.useYn = "N";

        //when
        Review updateReviewResult = reviewService.updateReview(review, 6L);
        System.out.println("result = " + updateReviewResult);

        //then
        Assertions.assertEquals("난 좋은데..11", updateReviewResult.title);
    }

    @Test
    @Transactional
    void getReviews() {
        //given
        ReviewDto params = new ReviewDto();
        params.size = 10;
        params.page = 1;

        //when
        Page<Review> reviews = reviewService.getReviews(params);
        System.out.println("result = " + reviews.getContent());

        //then
        Assertions.assertEquals(1, reviews.getTotalPages());
    }

    @Test
    @Transactional
    void getReview() {
        //given
        Long id = 2L;

        //when
        Review getReviewResult = reviewService.getReview(id);
        System.out.println("result = " + getReviewResult.book);

        //then
        Assertions.assertEquals("저는 약간...", getReviewResult.title);
    }

}
