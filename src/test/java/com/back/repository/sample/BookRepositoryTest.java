package com.back.repository.sample;

import com.back.domain.sample.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.bookNm = "과학동아6";
        book.author = "홍길동6";

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }
}
