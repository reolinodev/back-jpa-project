package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.params.BoardParam;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_BOARD", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//게시판 정보
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //게시판명
    public String boardTitle;

    //게시판유형
    public String boardType;

    //비고
    public String memo;

    //첨부파일여부
    public String attachYn;

    //댓글여부
    public String commentYn;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<BoardAuth> boardAuths = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<Faq> faqs = new ArrayList<>();

    public void setCreateParam(BoardParam boardParam) {
        this.boardTitle = boardParam.boardTitle;
        this.boardType = boardParam.boardType;
        this.memo = boardParam.memo;
        this.attachYn = boardParam.attachYn;
        this.commentYn = boardParam.commentYn;
        this.createdId = boardParam.createdId;
        this.useYn = "Y";
    }

    public void setUpdateParam(BoardParam boardParam) {

        this.updatedId = boardParam.updatedId;

        if(boardParam.boardTitle != null){
            this.boardTitle = boardParam.boardTitle;
        }

        if(boardParam.useYn != null){
            this.useYn = boardParam.useYn;
        }

        if(boardParam.memo != null){
            this.memo = boardParam.memo;
        }

        if(boardParam.attachYn != null){
            this.attachYn = boardParam.attachYn;
        }

        if(boardParam.commentYn != null){
            this.commentYn = boardParam.commentYn;
        }
    }
}

