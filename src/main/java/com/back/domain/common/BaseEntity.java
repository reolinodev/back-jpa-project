package com.back.domain.common;

import com.back.domain.listener.Auditable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {

    //등록일시
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //수정일시
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

    //사용여부
    @ColumnDefault("'Y'")
    public String useYn;

}
