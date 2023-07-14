package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="TB_BOOK", schema = "sample")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String bookNm;

    public String author;

    public String storeDt;

    public String rentalDt;

    @ColumnDefault("'Y'")
    public String useYn;

    @ColumnDefault("'N'")
    public String rentalYn;

//    @OneToOne(mappedBy = "book")
//    @ToString.Exclude
//    private BookReviewInfo bookReviewInfo;
//
//    @OneToMany
//    @JoinColumn(name = "book_id")
//    @ToString.Exclude
//    private List<Review> reviews = new ArrayList<>();

//    @OneToMany(mappedBy = "book")
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    public List<Rental> rentals = new ArrayList<>();

}
