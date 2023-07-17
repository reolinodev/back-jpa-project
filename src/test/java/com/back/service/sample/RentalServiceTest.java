package com.back.service.sample;

import com.back.domain.sample.Rental;
import com.back.domain.sample.RentalDto;
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
        RentalDto rentalDto = new RentalDto();
        rentalDto.userId = 1L;
        rentalDto.bookId = 2L;

        RentalDto rentalDto2 = new RentalDto();
        rentalDto2.userId = 1L;
        rentalDto2.bookId = 3L;

        RentalDto rentalDto3 = new RentalDto();
        rentalDto3.userId = 1L;
        rentalDto3.bookId = 4L;

        RentalDto rentalDto4 = new RentalDto();
        rentalDto4.userId = 1L;
        rentalDto4.bookId = 5L;

        RentalDto rentalDto5 = new RentalDto();
        rentalDto5.userId = 1L;
        rentalDto5.bookId = 6L;

        List<RentalDto> rentals = new ArrayList<>();
        rentals.add(rentalDto);
        rentals.add(rentalDto2);
        rentals.add(rentalDto3);
        rentals.add(rentalDto4);
        rentals.add(rentalDto5);

        //when
        List<Rental> createRentalResult = rentalService.createRentals(rentals);
        System.out.println("result = " + createRentalResult);

        //then
        Assertions.assertEquals(5, createRentalResult.size());
    }

    @Test
    void returnRental() {
        //given
        Long id = 26L;

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
        RentalDto rentalDto = new RentalDto();
        rentalDto.size = 10;
        rentalDto.page = 1;

        //when
        Page<Rental> rentals = rentalService.getRentals(rentalDto);
        System.out.println("result = " + rentals);

        //then
        Assertions.assertEquals(1, rentals.getTotalPages());
    }

}
