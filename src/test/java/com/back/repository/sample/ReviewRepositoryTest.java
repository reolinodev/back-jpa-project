package com.back.repository.sample;

import com.back.domain.sample.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void countByBook(){
        Book book = bookRepository.findById(2L).orElseThrow(RuntimeException::new);

        int countByBookResult = reviewRepository.countByBookAndUseYn(book, "Y");

        System.out.println("countByBookResult : " + countByBookResult);

    }

}
