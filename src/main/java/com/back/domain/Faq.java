package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.params.FaqParam;
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
@Table(name="TB_FAQ", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//FAQ 정보
public class Faq extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //제목
    public String faqTitle;

    //본문
    public String mainText;

    //조회수
    public int viewCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Board board;

    public void setCreateParam(FaqParam faqParam) {
        this.faqTitle = faqParam.faqTitle;
        this.mainText = faqParam.mainText;
        this.createdId = faqParam.createdId;
        this.useYn = "Y";
    }


    public void setUpdateParam(FaqParam faqParam) {

        this.updatedId = faqParam.updatedId;

        if(faqParam.faqTitle != null){
            this.faqTitle = faqParam.faqTitle;
        }

        if(faqParam.useYn != null){
            this.useYn = faqParam.useYn;
        }

        if(faqParam.mainText != null){
            this.mainText = faqParam.mainText;
        }
    }

}

