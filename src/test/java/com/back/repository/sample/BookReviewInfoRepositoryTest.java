package com.back.repository.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findByBookId() {
        Book book =  bookRepository.findById(2L).orElseThrow(RuntimeException::new);

        BookReviewInfo bookReviewInfo = bookReviewInfoRepository.findByBook(book);
        System.out.println("bookReviewInfo :" + bookReviewInfo);
    }

}
