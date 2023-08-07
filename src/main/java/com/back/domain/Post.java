package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.params.PostParam;
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
@Table(name="TB_POST", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//게시글 정보
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //게시글제목
    public String postTitle;

    //본문
    public String mainText;

    //조회수
    public int viewCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    public Board board;

    public void setCreateParam(PostParam postParam) {
        this.postTitle = postParam.postTitle;
        this.mainText = postParam.mainText;
        this.createdId = postParam.createdId;
        this.useYn = "Y";
    }


    public void setUpdateParam(PostParam postParam) {

        this.updatedId = postParam.updatedId;

        if(postParam.postTitle != null){
            this.postTitle = postParam.postTitle;
        }

        if(postParam.useYn != null){
            this.useYn = postParam.useYn;
        }

        if(postParam.mainText != null){
            this.mainText = postParam.mainText;
        }
    }
}

