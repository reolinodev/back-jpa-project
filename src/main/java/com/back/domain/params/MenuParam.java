package com.back.domain.params;

import com.back.domain.common.ValidationGroups.MenuCreateGroup;
import com.back.domain.common.ValidationGroups.MenuUpdateGroup;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class MenuParam {

    //메뉴명
    @NotEmpty(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, message = "메뉴명을 입력하세요.")
    @Size(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, max=50, message = "30글자 이내로 입력하세요.")
    public String menuNm;

    //메뉴레벨
    public int menuLv;

    //상위식별키
    public Long prnMenuId;

    //권한구분
    @NotEmpty(groups = { MenuCreateGroup.class }, message = "권한구분을 입력하세요.")
    public String authRole;

    //url
    @Size(groups = { MenuCreateGroup.class, MenuUpdateGroup.class}, max=200, message = "200글자 이내로 입력하세요.")
    public String url;

    //순서
    public int ord;

    //사용여부
    @NotEmpty(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, message = "사용여부를 입력하세요.")
    public String useYn;

    //메뉴타입
    @NotEmpty(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, message = "메뉴유형을 입력하세요.")
    @Size(groups = { MenuCreateGroup.class, MenuUpdateGroup.class}, max=10, message = "10글자 이내로 입력하세요.")
    public String menuType;

    //게시판아이디
    public Long boardId;

    //게시판유형
    public String boardType;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;
}
