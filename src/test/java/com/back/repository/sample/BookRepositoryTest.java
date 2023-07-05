package com.back.repository.sample;

import com.back.domain.sample.Book;
import org.assertj.core.util.Lists;
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
        book.bookNm = "삼국지1";
        book.author = "나관중";

        Book book2 = new Book();
        book2.bookNm = "삼국지2";
        book2.author = "나관중";

        Book book3 = new Book();
        book3.bookNm = "나무";
        book3.author = "베르베르";

        Book book4 = new Book();
        book4.bookNm = "신곡";
        book4.author = "괴테";

        bookRepository.saveAll(Lists.newArrayList(book, book2, book3, book4));

        System.out.println(bookRepository.findAll());
    }
}
