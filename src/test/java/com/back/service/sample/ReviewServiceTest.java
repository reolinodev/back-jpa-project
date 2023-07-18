package com.back.service.sample;

import com.back.domain.sample.Review;
import com.back.domain.sample.params.ReviewParam;
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
        ReviewParam reviewDto = new ReviewParam();
        reviewDto.userId = 3L;
        reviewDto.bookId = 7L;
        reviewDto.score = 5.0f;
        reviewDto.title = "난 좋은데..7";
        reviewDto.content ="너무 재미있네요.7";

        //when
        Review createReviewResult = reviewService.createReview(reviewDto);
        System.out.println("result = " + createReviewResult);

        //then
        Assertions.assertEquals("난 좋은데..7", createReviewResult.title);
    }

    @Test
    void updateReview() {
        //given
        ReviewParam reviewParam = new ReviewParam();
        reviewParam.score = 3.0f;
        reviewParam.title = "난 좋은데..11";
        reviewParam.content ="너무 재미있네요...11";
        reviewParam.useYn = "N";

        //when
        Review updateReviewResult = reviewService.updateReview(reviewParam, 6L);
        System.out.println("result = " + updateReviewResult);

        //then
        Assertions.assertEquals("난 좋은데..11", updateReviewResult.title);
    }

    @Test
    @Transactional
    void getReviews() {
        //given
        ReviewParam params = new ReviewParam();
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
