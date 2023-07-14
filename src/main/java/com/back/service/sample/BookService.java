package com.back.service.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.BookDto;
import com.back.repository.sample.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Page<Book> getBooks(BookDto bookDto) {
        bookDto.setPageIdx(bookDto.page);
        return bookRepository.findBooksBy(PageRequest.of(bookDto.page,bookDto.size, Sort.by(Order.desc("id"))));
    }
    /**
     * 책을 상세 조회 합니다.
     */
    public Book getBook(long id) {
        return bookRepository.findById(id).get();
    }


    /**
     * 책이 존재하는지 체크합니다.
     */
    public int checkBook(String bookNm, String author) {
        return bookRepository.countByBookNmAndAuthor(bookNm,author);
    }

    /**
     * 책을 생성합니다.
     */
    public List<Book> createBooks(List<Book> books) {
       return bookRepository.saveAll(books);
    }


    /**
     * 책을 수정합니다.
     */
    public Book updateBook(Book book, Long id) {
        book.id = id;
        return bookRepository.save(book);
    }

}
