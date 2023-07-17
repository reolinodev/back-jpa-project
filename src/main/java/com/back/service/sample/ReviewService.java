package com.back.service.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.BookReviewInfo;
import com.back.domain.sample.Dept;
import com.back.domain.sample.DeptDto;
import com.back.domain.sample.Review;
import com.back.domain.sample.ReviewDto;
import com.back.repository.sample.BookRepository;
import com.back.repository.sample.BookReviewInfoRepository;
import com.back.repository.sample.DeptRepository;
import com.back.repository.sample.ReviewRepository;
import com.back.repository.sample.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final BookReviewInfoRepository bookReviewInfoRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    /**
     * todo 부서를 전체조회 합니다. (동적 쿼리 변경)
     */
    public Page<Review> getReviews(ReviewDto reviewDto) {
        reviewDto.setPageIdx(reviewDto.page);
        return reviewRepository.findReviewsBy(PageRequest.of(reviewDto.page,reviewDto.size, Sort.by(Order.desc("id"))));
    }

    /**
     * 리뷰를 상세조회 합니다.
     */
    public Review getReview(long id) {
        return reviewRepository.findById(id).get();
    }

    /**
     * 리뷰를 생성합니다.
     */
    public Review createReview(ReviewDto reviewDto) {
        Review review = Review.builder()
            .user(userRepository.findById(reviewDto.userId).orElseThrow(RuntimeException::new))
            .book(bookRepository.findById(reviewDto.bookId).orElseThrow(RuntimeException::new))
            .title(reviewDto.title)
            .content(reviewDto.content)
            .score(reviewDto.score)
            .build();

        Review createReviewResult = reviewRepository.saveAndFlush(review);

        Book book = createReviewResult.book;
        if(book != null){
            BookReviewInfo findByBookResult = bookReviewInfoRepository.findByBook(book);

            BookReviewInfo params = new BookReviewInfo();

            if(findByBookResult != null){
                params.id = findByBookResult.id;
            }

            params.book = book;
            params.reviewCount = reviewRepository.countByBookAndUseYn(book, "Y");
            //todo 그룹함수 평균총점
            bookReviewInfoRepository.save(params);
        }

       return createReviewResult;
    }


    /**
     * 리뷰를 수정합니다.
     */
    public Review updateReview(Review review, Long id) {
        review.id = id;
        Review findByIdResult = reviewRepository.findById(id).orElseThrow(RuntimeException::new);
        findByIdResult.title = review.title;
        findByIdResult.content = review.content;
        findByIdResult.score = review.score;
        findByIdResult.useYn = review.useYn;

        Review updateReviewResult = reviewRepository.save(findByIdResult);

        Book book = updateReviewResult.book;

        if(book != null){
            BookReviewInfo findByBookResult = bookReviewInfoRepository.findByBook(book);
            BookReviewInfo params = new BookReviewInfo();
            if(findByBookResult != null){
                params.id = findByBookResult.id;
            }

            params.book = book;
            params.reviewCount = reviewRepository.countByBookAndUseYn(book, "Y");
            //todo 그룹함수 평균총점
            bookReviewInfoRepository.save(params);
        }

        return reviewRepository.save(findByIdResult);
    }

}
