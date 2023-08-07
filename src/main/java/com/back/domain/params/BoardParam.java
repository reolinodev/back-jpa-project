package com.back.domain.params;

import com.back.domain.common.Page;
import com.back.domain.common.ValidationGroups.BoardCreateGroup;
import com.back.domain.common.ValidationGroups.BoardUpdateGroup;
import javax.validation.constraints.NotEmpty;
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
//게시판 정보
public class BoardParam extends Page {

    @NotEmpty(groups = {BoardCreateGroup.class, BoardUpdateGroup.class}, message = "게시판명을 입력하세요.")
    public String boardTitle;

    @NotEmpty(groups = {BoardCreateGroup.class}, message = "게시판유형을 입력하세요.")
    public String boardType;

    @NotEmpty(groups = { BoardUpdateGroup.class  }, message = "사용여부를 입력하세요.")
    public String useYn;

    public String memo;

    @NotEmpty(groups = {BoardCreateGroup.class, BoardUpdateGroup.class}, message = "첨부파일 사용여부를 입력하세요.")
    public String attachYn;

    @NotEmpty(groups = {BoardCreateGroup.class, BoardUpdateGroup.class}, message = "댓글사용여부를 입력하세요.")
    public String commentYn;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

    //권한아이디 Array
    public Long[] authIdArr;


}



