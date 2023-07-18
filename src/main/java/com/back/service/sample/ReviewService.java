package com.back.service.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.BookReviewInfo;
import com.back.domain.sample.Review;
import com.back.domain.sample.params.ReviewParam;
import com.back.repository.sample.BookRepository;
import com.back.repository.sample.BookReviewInfoRepository;
import com.back.repository.sample.ReviewRepository;
import com.back.repository.sample.UserRepository;
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
     * todo 리뷰를 전체조회 합니다. (동적 쿼리 변경)
     */
    public Page<Review> getReviews(ReviewParam reviewParam) {
        reviewParam.setPaging(reviewParam.page);
        return reviewRepository.findReviewsBy(PageRequest.of(reviewParam.page,reviewParam.size, Sort.by(Order.desc("id"))));
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
    public Review createReview(ReviewParam reviewParam) {
        Review review = new Review();
        review.setReview(reviewParam);
        review.user = userRepository.findById(reviewParam.userId).orElseThrow(RuntimeException::new);
        review.book = bookRepository.findById(reviewParam.bookId).orElseThrow(RuntimeException::new);

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
    public Review updateReview(ReviewParam reviewParam, Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(RuntimeException::new);
        review.setReview(reviewParam);

        Review updateReviewResult = reviewRepository.saveAndFlush(review);

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

        return updateReviewResult;
    }

}
