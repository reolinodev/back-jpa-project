package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.sample.params.BookParam;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public String category;

    public String storeDt;

    public String rentalDt;

    @ColumnDefault("'Y'")
    public String useYn;

    @ColumnDefault("'N'")
    public String rentalYn;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    @ToString.Exclude
    public BookReviewInfo bookReviewInfo;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    public List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    public List<Rental> rentals = new ArrayList<>();

    public void setBook(BookParam bookParam) {
        this.bookNm = bookParam.bookNm;
        this.author = bookParam.author;
        this.category = bookParam.category;
        this.storeDt = bookParam.storeDt;
        this.useYn = bookParam.useYn;
    }

}
