package com.back.repository.sample;

import com.back.domain.sample.Rental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentalRepositoryTest {

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    void rentalTest() {
        Rental rental = new Rental();
        rental.userId = 6L;
        rental.bookId = 7L;
        rental.returnYn = "N";

        rentalRepository.save(rental);

        System.out.println(rentalRepository.findAll());
    }


    @Test
    void rentalTest2() {
        Rental rental = new Rental();
        rental.id = 1L;
        rental.returnYn = "Y";

        rentalRepository.save(rental);

        System.out.println(rentalRepository.findAll());
    }


}
