package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.params.CodeGrpParam;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_CODE_GRP", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class CodeGrp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //코드그룹명
    public String codeGrpNm;

    //코드그룹값
    public String codeGrpVal;

    @OneToMany(mappedBy = "codeGrp", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<Code> codes = new ArrayList<>();

    public void setCreateParam(CodeGrpParam codeGrpParam) {
        this.codeGrpNm = codeGrpParam.codeGrpNm;
        this.codeGrpVal = codeGrpParam.codeGrpVal;
        this.createdId = codeGrpParam.createdId;
        this.useYn = "Y";
    }

    public void setUpdateParam(CodeGrpParam codeGrpParam) {

        this.updatedId = codeGrpParam.updatedId;

        if(codeGrpParam.codeGrpNm != null){
            this.codeGrpNm = codeGrpParam.codeGrpNm;
        }

        if(codeGrpParam.useYn != null){
            this.useYn = codeGrpParam.useYn;
        }
    }
}
