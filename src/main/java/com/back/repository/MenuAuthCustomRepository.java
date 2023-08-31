package com.back.repository;

import static com.back.domain.QAuth.auth;
import static com.back.domain.QCode.code;
import static com.back.domain.QMenu.menu;
import static com.back.domain.QMenuAuth.menuAuth;

import com.back.domain.MenuAuth;
import com.back.domain.dto.AuthDto;
import com.back.domain.dto.MenuAuthDto;
import com.back.domain.dto.MenuDto;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuAuthCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllAuthByMenu
     * 기능 : 메뉴별 권한 목록 조회
     * 파라미터 : id
     */
    public List<MenuAuthDto> findAllMenuAuth(Long menuId, String authRole) {
        return queryFactory
            .select(
                Projections.bean(MenuAuthDto.class,
                    auth.id.as("authId"),
                    auth.authNm,
                    menuAuth.useYn.coalesce("N").as("useYn"),
                    ConvertUtils.getParseCodeNm("USE_YN", menuAuth.useYn.coalesce("N")).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menuAuth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(menuAuth.updatedAt).as("updatedAtLabel"),
                    menuAuth.createdId,
                    ConvertUtils.getParseUserNm(menuAuth.createdId).as("createdIdLabel"),
                    menuAuth.updatedId,
                    ConvertUtils.getParseUserNm(menuAuth.updatedId).as("updatedIdLabel")
                )
            )
            .from(auth)
            .leftJoin(auth.menuAuths, menuAuth).on(menuAuth.menu.id.eq(menuId))
            .where(
                auth.useYn.eq("Y"),
                auth.authRole.eq(authRole)
            )
            .orderBy(auth.ord.when("").then("99")
                .otherwise(auth.ord).castToNum(Integer.class).asc(), auth.createdAt.asc())
            .fetch();
    }

    /* 메소드명 : findByMenuIdAndAuthId
     * 기능 : 메뉴별 권한 목록 조회
     * 파라미터 : menuId, authId
     */
    public MenuAuth findByMenuIdAndAuthId(Long menuId, Long authId) {
        return queryFactory
            .select(
                Projections.bean(MenuAuth.class,
                    menuAuth.id,
                    menuAuth.menu,
                    menuAuth.auth,
                    menuAuth.useYn,
                    menuAuth.createdId,
                    menuAuth.updatedId,
                    menuAuth.createdAt,
                    menuAuth.updatedAt
                )
            )
            .from(menuAuth)
            .where(
                menuAuth.menu.id.eq(menuId),
                menuAuth.auth.id.eq(authId)
            )
            .fetchOne();
    }


    /************************* 조건절 ***************************/
}
