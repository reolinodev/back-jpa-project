package com.back.domain.sample;

import com.back.domain.common.BaseEntity;
import com.back.domain.sample.params.CodeGrpParam;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="TB_CODE_GRP", schema = "sample")
public class CodeGrp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String codeGrpNm;

    public String codeGrpValue;

    public String codeGrpDetail;

    @ColumnDefault("'Y'")
    public String useYn;

    @OneToMany(mappedBy = "codeGrp")
    @ToString.Exclude
    public List<Code> codes = new ArrayList<>();

    public void setCodeGrp(CodeGrpParam codeGrpParam) {
        this.codeGrpNm = codeGrpParam.codeGrpNm;
        this.codeGrpValue = codeGrpParam.codeGrpValue;
        this.codeGrpDetail = codeGrpParam.codeGrpDetail;
        this.useYn = codeGrpParam.useYn;
    }

}
