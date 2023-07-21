package com.back.domain.sample;

import com.back.domain.sample.common.BaseEntity;
import com.back.domain.sample.params.RentalParam;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="TB_RENTAL", schema = "sample")
public class Rental extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String rentalDt;

    public String returnDt;

    public String returnYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Book book;

    public void setRental(RentalParam rentalParam) {
        this.rentalDt = rentalParam.rentalDt;
        this.returnDt = rentalParam.returnDt;
        this.returnYn = rentalParam.returnYn;
    }

}
