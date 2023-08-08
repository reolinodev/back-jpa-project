package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.FaqCreateGroup;
import com.back.domain.common.ValidationGroups.FaqUpdateGroup;
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
public class FaqParam extends Page {

    //게시판아이디
    @NotNull(groups = { FaqCreateGroup.class}, message = "게시판식별키를 입력하세요.")
    public long boardId;

    //제목
    @NotEmpty(groups = { FaqCreateGroup.class, FaqUpdateGroup.class }, message = "제목을 입력하세요.")
    public String faqTitle;

    //본문
    @NotEmpty(groups = { FaqCreateGroup.class, FaqUpdateGroup.class }, message = "본문을 입력하세요.")
    public String mainText;

    //사용여부
    @NotEmpty(groups = { FaqUpdateGroup.class }, message = "사용여부를 입력하세요.")
    public String useYn;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;
}

