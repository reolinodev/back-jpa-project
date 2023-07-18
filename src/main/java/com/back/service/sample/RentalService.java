package com.back.service.sample;

import com.back.domain.sample.Book;
import com.back.domain.sample.Rental;
import com.back.domain.sample.params.RentalParam;
import com.back.repository.sample.BookRepository;
import com.back.repository.sample.RentalRepository;
import com.back.repository.sample.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    /**
     * 대여를 전체 조회 합니다.
     */
    public Page<Rental> getRentals(RentalParam rentalParam) {
        rentalParam.setPaging(rentalParam.page);
        return rentalRepository.findRentalsBy(PageRequest.of(rentalParam.page,rentalParam.size, Sort.by(Order.desc("id"))));
    }
    /**
     *  대여를 상세 조회 합니다.
     */
    public Rental getRental(long id) {
        return rentalRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    /**
     * 대여 정보를 생성합니다.
     */
    public List<Rental> createRentals(List<RentalParam> rentalParams) {
        List<Rental> list = new ArrayList<>();
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        for (RentalParam rentalParam : rentalParams){
            Rental rental = Rental.builder()
                .book(bookRepository.findById(rentalParam.bookId).orElseThrow(RuntimeException::new))
                .user(userRepository.findById(rentalParam.userId).orElseThrow(RuntimeException::new))
                .rentalDt(currentTime)
                .returnYn("N")
                .build();

            list.add(rental);
        }

        List<Rental> saveAllResult = rentalRepository.saveAll(list);
        if(saveAllResult.size() != 0) {
            for (Rental rental : saveAllResult) {
                Book book = rental.book;
                book.rentalYn = "Y";
                book.rentalDt = currentTime;
                bookRepository.save(book);
            }
        }

        return saveAllResult;
    }


    /**
     * 반납합니다.
     */
    public Rental returnRental(Long id) {

        Rental rental = rentalRepository.findById(id).orElseThrow(RuntimeException::new);
        rental.returnDt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        rental.returnYn = "Y";

        Rental saveResult = rentalRepository.save(rental);

        Book book = saveResult.book;
        book.rentalDt = null;
        book.rentalYn = "N";
        bookRepository.save(book);

        return saveResult;
    }

}
