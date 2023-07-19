package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.sample.params.CodeParam;
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
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="TB_CODE", schema = "sample")
public class Code extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String codeNm;

    public String codeValue;

    public String codeDetail;

    @ColumnDefault("'Y'")
    public String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public CodeGrp codeGrp;

    public void setCode(CodeParam codeParam) {
        this.codeNm = codeParam.codeNm;
        this.codeValue = codeParam.codeValue;
        this.codeDetail = codeParam.codeDetail;
        this.useYn = codeParam.useYn;
    }

}
