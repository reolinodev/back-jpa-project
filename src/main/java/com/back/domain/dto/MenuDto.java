package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class MenuDto {
    //메뉴아이디
    public Long menuId;

    //메뉴명
    public String menuNm;

    //메뉴레벨
    public int menuLv;

    //상위메뉴아이디
    public Long prnMenuId;

    //상위메뉴명
    public String prnMenuNm;

    //권한구분
    public String authRole;

    //url
    public String url;

    //순서
    public int ord;

    //메뉴타입
    public String menuType;

    //게시판아이디
    public Long boardId;

    //게시판유형
    public String boardType;

    //사용여부
    public String useYn;

    //네비게이션 구성여부
    public String navYn;

    //사용여부-라벨
    public String useYnLabel;

    //등록일-라벨
    public String createdAtLabel;

    //수정일-라벨
    public String updatedAtLabel;

    //등록자
    public Long createdId;

    //등록자-라벨
    public String createdIdLabel;

    //수정자
    public Long updatedId;

    //수정자-라벨
    public String updatedIdLabel;
}
