package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.QnaAdminUpdateGroup;
import com.back.domain.common.ValidationGroups.QnaCreateGroup;
import com.back.domain.common.ValidationGroups.QnaUserUpdateGroup;
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
public class QnaParam extends Page {

    public Long qnaId;

    @NotNull(groups = { QnaCreateGroup.class}, message = "게시판아이디를 입력하세요.")
    public Long boardId;

    @NotEmpty(groups = { QnaCreateGroup.class, QnaUserUpdateGroup.class }, message = "제목을 입력하세요.")
    public String qnaTitle;

    @NotEmpty(groups = { QnaCreateGroup.class, QnaUserUpdateGroup.class }, message = "질문을 입력하세요.")
    public String questions;

    @NotEmpty(groups = { QnaAdminUpdateGroup.class }, message = "답변을 입력하세요.")
    public String mainText;

    @NotEmpty(groups = { QnaCreateGroup.class, QnaUserUpdateGroup.class }, message = "비밀글 여부를 입력하세요.")
    public String hiddenYn;

    @NotNull(groups = { QnaCreateGroup.class, QnaUserUpdateGroup.class }, message = "비밀글 패스워드를 입력하세요.")
    public String qnaPw;

    @NotEmpty(groups = { QnaCreateGroup.class, QnaUserUpdateGroup.class }, message = "사용여부를 입력하세요.")
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

    public String responseYn;
}

