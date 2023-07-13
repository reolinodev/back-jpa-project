package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import java.util.ArrayList;
import java.util.List;
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
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="TB_DEPT", schema = "sample")
public class Dept extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String deptNm;

    public String deptCd;

    public String upperDeptCd;

    public String useYn;

//    @OneToOne(mappedBy = "book")
//    @ToString.Exclude
//    private BookReviewInfo bookReviewInfo;
//
//    @OneToMany
//    @JoinColumn(name = "book_id")
//    @ToString.Exclude
//    private List<Review> reviews = new ArrayList<>();
//
//    @OneToMany
//    @JoinColumn(name = "book_id")
//    @ToString.Exclude
//    private List<Rental> rentals = new ArrayList<>();

}
