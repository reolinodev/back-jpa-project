package com.back.repository.sample;

import static org.junit.jupiter.api.Assertions.*;

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
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(bookRepository.findById(2L).orElseThrow(RuntimeException::new));
        bookReviewInfo.setAverageReviewScore(4.3f);
        bookReviewInfo.setReviewCount(10);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    private Book givenBook() {
        Book book = new Book();
        book.bookNm = "공포소설2";
        book.useYn = "Y";

        return bookRepository.save(book);
    }

    @Test
    void crudTest2() {
//        givenBookReviewInfo();

//        Book result = bookReviewInfoRepository
//            .findById(6L)
//            .orElseThrow(RuntimeException::new)
//            .getBook();
//
//        System.out.println(">>> " + result);

        BookReviewInfo result2 = bookRepository
            .findById(6L)
            .orElseThrow(RuntimeException::new)
            .getBookReviewInfo();

        System.out.println(">>> " + result2);
    }


    @Test
    void crudTest3() {
        BookReviewInfo result = bookReviewInfoRepository.findByBookId(2L);

        System.out.println(">>> " + result);

    }



}
