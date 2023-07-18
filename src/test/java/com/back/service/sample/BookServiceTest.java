package com.back.service.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.params.BookParam;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;


    @Test
    void checkBook() {
        //given
        String bookNm="홍길동전";
        String author="허균";

        //when
        int checkBookResult = bookService.checkBook(bookNm, author);
        System.out.println("result = " + checkBookResult);

        //then
        Assertions.assertEquals(0, checkBookResult);
    }

    @Test
    void createBook() {
        //given
        BookParam book = BookParam.builder()
            .bookNm("임꺽정1")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book2 = BookParam.builder()
            .bookNm("임꺽정2")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book3 = BookParam.builder()
            .bookNm("임꺽정3")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book4 = BookParam.builder()
            .bookNm("임꺽정4")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book5 = BookParam.builder()
            .bookNm("임꺽정5")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book6 = BookParam.builder()
            .bookNm("임꺽정6")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book7 = BookParam.builder()
            .bookNm("임꺽정7")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book8 = BookParam.builder()
            .bookNm("임꺽정8")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book9 = BookParam.builder()
            .bookNm("임꺽정9")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book10 = BookParam.builder()
            .bookNm("임꺽정10")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book11 = BookParam.builder()
            .bookNm("임꺽정11")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        BookParam book12 = BookParam.builder()
            .bookNm("임꺽정12")
            .author("황석영")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        List<BookParam> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);
        books.add(book12);

        //when
        List<Book> createBookResult = bookService.createBooks(books);
        System.out.println("result = " + createBookResult);

        //then
        Assertions.assertEquals(12, createBookResult.size());
    }


    @Test
    void updateBook() {
        //given
        Long bookId = 1L;
        BookParam bookParam = new BookParam();
        bookParam.storeDt = "20230714";
        bookParam.useYn ="Y";
        bookParam.bookNm = "홍길동전";
        bookParam.author = "허균";

        //when
        Book updateBookResult = bookService.updateBook(bookParam, bookId);
        System.out.println("result = " + updateBookResult);

        //then
        Assertions.assertEquals("20230714", updateBookResult.storeDt);
    }


    @Test
    @Transactional
    void getBook() {
        //given
        Long bookId=2L;

        //when
        Book getBookResult = bookService.getBook(bookId);
        System.out.println("result = " + getBookResult);
        System.out.println("result2 = " + getBookResult.rentals);
        System.out.println("result3 = " + getBookResult.bookReviewInfo);

        //then
        Assertions.assertEquals("임꺽정2", getBookResult.bookNm);
    }

    @Test
    @Transactional
    void getBooks() {
        //given
        BookParam bookParam = new BookParam();
        bookParam.size = 10;
        bookParam.page = 1;

        //when
        Page<Book> books = bookService.getBooks(bookParam);
        System.out.println("result = " + books);

        //then
        Assertions.assertEquals(2, books.getTotalPages());
    }
}
