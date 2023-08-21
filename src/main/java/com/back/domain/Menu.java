package com.back.domain;

import com.back.domain.common.BaseEntity;
import com.back.domain.params.MenuParam;
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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_MENU", schema = "ws")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //메뉴명
    public String menuNm;

    //메뉴레벨
    public int menuLv;

    //상위식별키
    public Long prnMenuId;

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

    //네비게이션 구성여부
    @ColumnDefault("'Y'")
    public String navYn;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<MenuAuth> menuAuths = new ArrayList<>();

    public void setCreateParam(MenuParam menuParam) {
        this.menuNm = menuParam.menuNm;
        this.menuLv = menuParam.menuLv;

        if(menuParam.menuLv != 1){
            this.prnMenuId = menuParam.prnMenuId;
        }

        this.authRole = menuParam.authRole;
        this.url = menuParam.url;

        if(menuParam.ord == 0){
            menuParam.ord = 99;
        }else{
            this.ord = menuParam.ord;
        }

        this.useYn = menuParam.useYn;
        this.menuType = menuParam.menuType;

        if("BOARD".equals(menuType)){
            this.boardId = menuParam.boardId;
            this.boardType = menuParam.boardType;
        }

        this.navYn = menuParam.navYn;

        this.createdId = menuParam.createdId;
    }

    public void setUpdateParam(MenuParam menuParam) {

        this.updatedId = menuParam.updatedId;

        if(menuParam.menuNm != null){
            this.menuNm = menuParam.menuNm;
        }

        if(menuParam.ord != 0){
            this.ord = menuParam.ord;
        }else{
            this.ord = 99;
        }

        if(menuParam.useYn != null){
            this.useYn = menuParam.useYn;
        }

        if(menuParam.menuType != null){
            this.menuType = menuParam.menuType;
        }

        if(menuParam.useYn != null){
            this.useYn = menuParam.useYn;
        }

        if(menuParam.navYn != null){
            this.navYn = menuParam.navYn;
        }

        if("BOARD".equals(menuType)) {
            if(menuParam.boardId != null){
                this.boardId = menuParam.boardId;
            }

            if(menuParam.boardType != null){
                this.boardType = menuParam.boardType;
            }
        }else{
            this.boardId = null;
            this.boardType = null;
        }
    }
}
