package com.back.service.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.BookDto;
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
        Book book = Book.builder()
            .bookNm("삼국지1")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book2 = Book.builder()
            .bookNm("삼국지2")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book3 = Book.builder()
            .bookNm("삼국지3")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book4 = Book.builder()
            .bookNm("삼국지4")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book5 = Book.builder()
            .bookNm("삼국지5")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book6 = Book.builder()
            .bookNm("삼국지6")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book7 = Book.builder()
            .bookNm("삼국지7")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book8 = Book.builder()
            .bookNm("삼국지8")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book9 = Book.builder()
            .bookNm("삼국지9")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book10 = Book.builder()
            .bookNm("삼국지10")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book11 = Book.builder()
            .bookNm("삼국지11")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        Book book12 = Book.builder()
            .bookNm("삼국지12")
            .author("나관중")
            .storeDt("20230713")
            .useYn("Y")
            .build();

        List<Book> books = new ArrayList<>();
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
        Book book = new Book();
        book.storeDt = "20230714";
        book.useYn ="Y";
        book.bookNm = "홍길동전";
        book.author = "허균";

        //when
        Book updateBookResult = bookService.updateBook(book, bookId);
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
        Assertions.assertEquals("삼국지2", getBookResult.bookNm);
    }

    @Test
    @Transactional
    void getBooks() {
        //given
        BookDto bookDto = new BookDto();
        bookDto.size = 10;
        bookDto.page = 1;

        //when
        Page<Book> books = bookService.getBooks(bookDto);
        System.out.println("result = " + books);

        //then
        Assertions.assertEquals(2, books.getTotalPages());
    }
}
