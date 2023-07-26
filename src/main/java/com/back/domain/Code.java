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
@Table(name="TB_CODE", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Code extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //코드명
    public String codeNm;

    //코드값
    public String codeVal;

    //비고
    public String memo;

    //순서
    public String ord;

    //상위코드
    public String prnCodeVal;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public CodeGrp codeGrp;
}
