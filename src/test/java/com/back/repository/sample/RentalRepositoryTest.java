package com.back.repository.sample;

import com.back.domain.sample.Rental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentalRepositoryTest {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void rentalTest() {
        Rental rental = new Rental();
        rental.setUser(userRepository.findById(3L).orElseThrow(RuntimeException::new));
//        rental.setBook(bookRepository.findById(1L).orElseThrow(RuntimeException::new));
        rental.rentalDt = "20230705";
        rental.returnDt = "20230707";
        rental.returnYn = "N";

        rentalRepository.save(rental);

        System.out.println(rentalRepository.findAll());
    }


//    @Test
//    void rentalTest2() {
//        RentalDto rentalDto = new RentalDto();
//        rentalDto.userId = 3L;
//        rentalDto.bookId = 3L;
//        rentalDto.rentalDt = "20230705";
//        rentalDto.returnDt = "20230707";
//        rentalDto.returnYn = "N";
//
//        rentalRepository.save(rentalDto);
//
//        System.out.println(rentalRepository.findAll());
//    }


//    @Test
//    @Transactional
//    void rentalTest2() {
//       User user  = userRepository.findById(3L).orElseThrow(RuntimeException::new);
//
//        System.out.println("getRentals : " +user.getRentals());
//    }


}
