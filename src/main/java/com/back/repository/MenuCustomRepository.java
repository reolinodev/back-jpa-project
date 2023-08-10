package com.back.repository;

import static com.back.domain.QMenu.menu;

import com.back.domain.dto.MenuDto;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
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
    public List<MenuDto> findPrnMenuBy(String authRole) {
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
                menu.menuLv.eq(1),
                menu.useYn.eq("Y")
            )
            .orderBy(menu.ord.asc(), menu.createdAt.asc())
            .fetch();
    }


    /************************* 조건절 ***************************/
}
