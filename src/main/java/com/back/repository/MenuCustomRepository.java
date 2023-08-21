package com.back.repository;

import static com.back.domain.QMenu.menu;
import static com.back.domain.QMenuAuth.menuAuth;
import static com.back.domain.QUser.user;

import com.back.domain.dto.MenuDto;
import com.back.domain.params.MenuParam;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findMenuBy
     * 기능 : 메뉴상세 조회
     * 파라미터 : id
     */
    public MenuDto findMenuBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(MenuDto.class,
                    menu.id.as("menuId"),
                    menu.menuNm,
                    menu.menuLv,
                    menu.prnMenuId,
                    menu.authRole,
                    menu.url,
                    menu.ord,
                    menu.menuType,
                    menu.boardType,
                    menu.boardId,
                    menu.navYn,
                    menu.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", menu.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.updatedAt).as("updatedAtLabel"),
                    menu.createdId,
                    ConvertUtils.getParseUserNm(menu.createdId).as("createdIdLabel"),
                    menu.updatedId,
                    ConvertUtils.getParseUserNm(menu.updatedId).as("updatedIdLabel")
                )
            )
            .from(menu)
            .where(
                menu.id.eq(id)
            )
            .fetchOne();
    }

    /* 메소드명 : findPrnMenuBy
     * 기능 : 상위메뉴 조회
     * 파라미터 : authRole 권한구분
     */
    public List<MenuDto> findMenuByAuthRoleAndMenuLv(String authRole, int menuLv) {
        return queryFactory
            .select(
                Projections.bean(MenuDto.class,
                    menu.id.as("menuId"),
                    menu.menuNm,
                    menu.menuLv,
                    menu.prnMenuId,
                    menu.authRole,
                    menu.url,
                    menu.ord,
                    menu.menuType,
                    menu.boardType,
                    menu.boardId,
                    menu.useYn,
                    menu.navYn,
                    ConvertUtils.getParseCodeNm("USE_YN", menu.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.updatedAt).as("updatedAtLabel"),
                    menu.createdId,
                    ConvertUtils.getParseUserNm(menu.createdId).as("createdIdLabel"),
                    menu.updatedId,
                    ConvertUtils.getParseUserNm(menu.updatedId).as("updatedIdLabel")
                )
            )
            .from(menu)
            .where(
                menu.authRole.eq(authRole),
                menu.menuLv.eq(menuLv),
                menu.useYn.eq("Y")
            )
            .orderBy(menu.ord.asc(), menu.createdAt.asc())
            .fetch();
    }



    /* 메소드명 : findPrnMenuBy
     * 기능 : 상위메뉴 조회
     * 파라미터 : authRole 권한구분
     */
    public List<MenuDto> getMenus(MenuParam menuParam) {
        return queryFactory
            .select(
                Projections.bean(MenuDto.class,
                    menu.id.as("menuId"),
                    menu.menuNm,
                    menu.menuLv,
                    menu.prnMenuId,
                    menu.authRole,
                    menu.url,
                    menu.ord,
                    menu.menuType,
                    menu.boardType,
                    menu.boardId,
                    menu.useYn,
                    menu.navYn,
                    ConvertUtils.getParseCodeNm("USE_YN", menu.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.updatedAt).as("updatedAtLabel"),
                    menu.createdId,
                    ConvertUtils.getParseUserNm(menu.createdId).as("createdIdLabel"),
                    menu.updatedId,
                    ConvertUtils.getParseUserNm(menu.updatedId).as("updatedIdLabel")
                )
            )
            .from(menu)
            .where(
                authRoleEq(menuParam.authRole),
                menuLvEq(menuParam.menuLv),
                useYnEq(menuParam.useYn)
            )
            .orderBy(menu.ord.asc(), menu.createdAt.asc())
            .fetch();
    }


    /* 메소드명 : getMenusByAuth
     * 기능 : 권한별 메뉴 조회
     * 파라미터 : MenuParam 메뉴 파라미터
     */
    public List<MenuDto> getMenusByAuth(MenuParam menuParam) {
        return queryFactory
            .select(
                Projections.bean(MenuDto.class,
                    menu.id.as("menuId"),
                    menu.menuNm,
                    menu.menuLv,
                    menu.prnMenuId,
                    ConvertUtils.getParseMenuNm(menu.prnMenuId).as("prnMenuNm"),
                    menu.authRole,
                    menu.url,
                    menu.ord,
                    menu.menuType,
                    menu.boardType,
                    menu.boardId,
                    menu.useYn,
                    menu.navYn,
                    ConvertUtils.getParseCodeNm("USE_YN", menu.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menu.updatedAt).as("updatedAtLabel"),
                    menu.createdId,
                    ConvertUtils.getParseUserNm(menu.createdId).as("createdIdLabel"),
                    menu.updatedId,
                    ConvertUtils.getParseUserNm(menu.updatedId).as("updatedIdLabel")
                )
            )
            .from(menu)
            .join(menu.menuAuths, menuAuth)
            .where(
                menuAuth.auth.id.eq(menuParam.authId),
                menuAuth.useYn.eq("Y"),
                menu.navYn.eq("Y"),
                authRoleEq(menuParam.authRole),
                menuLvEq(menuParam.menuLv),
                useYnEq(menuParam.useYn)
            )
            .orderBy(menu.ord.asc(), menu.createdAt.asc())
            .fetch();
    }


    /************************* 조건절 ***************************/


    private BooleanExpression authRoleEq(String authRole){
        if(authRole == null){
            return null;
        }
        return menu.authRole.eq(authRole);
    }

    private BooleanExpression menuLvEq(int menuLv){
        if(menuLv == 0){
            return null;
        }
        return menu.menuLv.eq(menuLv);
    }

    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return menu.useYn.eq(useYn);
    }
}
