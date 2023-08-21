package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.PostCreateGroup;
import com.back.domain.common.ValidationGroups.PostUpdateGroup;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
//게시글 정보
public class PostParam extends Page {

    //게시판아이디
    @NotNull(groups = {PostCreateGroup.class}, message = "게시판 아이디를 입력하세요.")
    public Long boardId;

    //게시글제목
    @NotEmpty(groups = {PostCreateGroup.class, PostUpdateGroup.class}, message = "게시글제목을 입력하세요.")
    public String postTitle;

    //본문
    @NotEmpty(groups = {PostCreateGroup.class, PostUpdateGroup.class}, message = "본문을 입력하세요.")
    public String mainText;

    //사용여부
    @NotEmpty(groups = {PostUpdateGroup.class}, message = "사용여부를 입력하세요.")
    public String useYn;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

    //시작일
    public String startDate;

    //종료일
    public String endDate;

    //등록자명
    public String createdNm;

    public Long authId;

}

