package com.back.repository.sample;

import com.back.domain.sample.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository <Rental, Long> {
    Page<Rental> findRentalsBy(Pageable pageable);
}

