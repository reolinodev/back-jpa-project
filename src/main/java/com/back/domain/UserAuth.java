package com.back.domain;

import com.back.domain.common.BaseEntity;
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
@Entity
@Table(name="TB_USER_AUTH", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//사용자권한 정보
public class UserAuth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Auth auth;

}
