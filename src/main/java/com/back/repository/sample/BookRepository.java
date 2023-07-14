package com.back.repository.sample;

import com.back.domain.sample.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
    int countByBookNmAndAuthor(String bookNm, String author);

    Page<Book> findBooksBy(Pageable pageable);
}

