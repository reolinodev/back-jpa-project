package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.common.listener.BookReviewInfoListener;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString(callSuper = true)
@Table(name="TB_REVIEW", schema = "sample")
@Entity
//@EntityListeners({BookReviewInfoListener.class})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public String content;

    public float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Book book;

}
