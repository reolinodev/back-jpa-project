package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.params.QnaParam;
import com.back.support.CryptUtils;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_QNA", schema = "ws")
@ToString(callSuper = true)
//QNA 정보
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //제목
    public String qnaTitle;

    //질의
    public String questions;

    //본문
    public String mainText;

    //비밀글여부
    public String hiddenYn;

    //문의비밀번호
    public String qnaPw;

    //응답여부
    public String responseYn;

    //답변자
    public Long responseId;

    //답변시간
    public LocalDateTime responseAt;

    //조회수
    public int viewCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Board board;

    public void setCreateParam(QnaParam qnaParam) throws NoSuchAlgorithmException {
        this.qnaTitle = qnaParam.qnaTitle;
        this.questions = qnaParam.questions;
        this.hiddenYn = qnaParam.hiddenYn;

        if("Y".equals(qnaParam.hiddenYn)&& qnaParam.qnaPw != null){
            this.qnaPw = CryptUtils.encryptSha256(qnaParam.qnaPw);
        }

        this.useYn = "Y";
        this.createdId = qnaParam.createdId;
    }

    public void setUpdateParam(QnaParam qnaParam) throws NoSuchAlgorithmException {

        this.updatedId = qnaParam.updatedId;

        if(qnaParam.qnaTitle != null){
            this.qnaTitle = qnaParam.qnaTitle;
        }

        if(qnaParam.questions != null){
            this.questions = qnaParam.questions;
        }

        if(qnaParam.mainText != null){
            this.mainText = qnaParam.mainText;
            this.responseId = qnaParam.updatedId;
            this.responseAt = LocalDateTime.now();
            this.responseYn = "Y";
        }

        if(qnaParam.hiddenYn != null){

            this.hiddenYn = qnaParam.hiddenYn;

            if("Y".equals(qnaParam.hiddenYn) && qnaParam.qnaPw != null){
                this.qnaPw = CryptUtils.encryptSha256(qnaParam.qnaPw);
            }else{
                this.qnaPw = null;
            }
        }

        if(qnaParam.useYn != null){
            this.useYn = qnaParam.useYn;
        }
    }


}

