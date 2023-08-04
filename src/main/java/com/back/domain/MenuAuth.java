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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_MENU_AUTH", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MenuAuth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Auth auth;

}
