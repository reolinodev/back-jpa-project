package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.sample.params.ReviewParam;
import com.back.domain.sample.params.UserParam;
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
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name="TB_REVIEW", schema = "sample")
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public String content;

    public float score;

    @ColumnDefault("'Y'")
    public String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Book book;

    public void setReview(ReviewParam reviewParam) {
        this.title = reviewParam.title;
        this.content = reviewParam.content;
        this.score = reviewParam.score;
        this.useYn = reviewParam.useYn;
    }

}
