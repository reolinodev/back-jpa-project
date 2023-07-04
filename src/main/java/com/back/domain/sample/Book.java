package com.back.domain.sample;

import com.back.domain.common.entityListener.Auditable;
import com.back.domain.common.entityListener.UpdateDateEntityListener;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name="TB_BOOK", schema = "test")
@Entity
@EntityListeners(UpdateDateEntityListener.class)
public class Book implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    public Long bookId;

    @Column(name="book_nm")
    public String bookNm;

    public String author;

    @Column(name="created_dt", updatable = false)
    public LocalDateTime createdAt;

    @Column(name="updated_dt")
    public LocalDateTime updatedAt;
}
