package com.back.service.sample;

import com.back.domain.sample.Rental;
import com.back.domain.sample.params.RentalParam;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Test
    void createRentals() {
        //given
        RentalParam rentalParam = new RentalParam();
        rentalParam.userId = 3L;
        rentalParam.bookId = 8L;

        RentalParam rentalParam2 = new RentalParam();
        rentalParam2.userId = 3L;
        rentalParam2.bookId = 9L;

        RentalParam rentalParam3 = new RentalParam();
        rentalParam3.userId = 3L;
        rentalParam3.bookId = 10L;

        RentalParam rentalParam4 = new RentalParam();
        rentalParam4.userId = 3L;
        rentalParam4.bookId = 11L;

        RentalParam rentalParam5 = new RentalParam();
        rentalParam5.userId = 3L;
        rentalParam5.bookId = 12L;

        List<RentalParam> rentals = new ArrayList<>();
        rentals.add(rentalParam);
        rentals.add(rentalParam2);
        rentals.add(rentalParam3);
        rentals.add(rentalParam4);
        rentals.add(rentalParam5);

        //when
        List<Rental> createRentalResult = rentalService.createRentals(rentals);
        System.out.println("result = " + createRentalResult);

        //then
        Assertions.assertEquals(5, createRentalResult.size());
    }

    @Test
    void returnRental() {
        //given
        Long id = 25L;

        //when
        Rental updateRentalResult = rentalService.returnRental(id);
        System.out.println("result = " + updateRentalResult);

        //then
        Assertions.assertEquals("Y", updateRentalResult.returnYn);
    }

    @Test
    @Transactional
    void getRental() {
        //given
        Long id = 24L;

        //when
        Rental getRentalResult = rentalService.getRental(id);
        System.out.println("result = " + getRentalResult);

        //then
        Assertions.assertEquals(4, getRentalResult.book.id);
    }


    @Test
    @Transactional
    void getRentals() {
        //given
        RentalParam rentalParam = new RentalParam();
        rentalParam.size = 10;
        rentalParam.page = 1;

        //when
        Page<Rental> rentals = rentalService.getRentals(rentalParam);
        System.out.println("result = " + rentals);

        //then
        Assertions.assertEquals(1, rentals.getTotalPages());
    }

}
