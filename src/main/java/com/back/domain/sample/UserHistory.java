package com.back.domain.sample;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name="TB_USER_HISTORY", schema = "test")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_history_id")
    public Long userHistoryId;

    @Column(name="user_id")
    public Long userId;

    @Column(name="menu_nm")
    public String menuNm;

    public String action;

    @Column(name="created_dt", updatable = false)
    @CreatedDate
    public LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
