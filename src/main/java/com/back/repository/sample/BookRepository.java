package com.back.repository.sample;

import com.back.domain.sample.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {

}

