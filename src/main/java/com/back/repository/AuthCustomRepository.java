package com.back.repository;

import static com.back.domain.QAuth.auth;

import com.back.domain.dto.AuthDto;
import com.back.domain.params.AuthParam;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllWithPaging
     * 기능 : 페이징 처리된 권한 조회
     * 파라미터 : AuthParam
     */
    public Page<AuthDto> findAllWithPaging(AuthParam authParam, Pageable pageable) {
        List<AuthDto> content = queryFactory
            .select(
                Projections.bean(AuthDto.class,
                    auth.id.as("authId"),
                    auth.authNm,
                    auth.authVal,
                    auth.authRole,
                    ConvertUtils.getParseCodeNm("AUTH_ROLE", auth.authRole).as("authRoleLabel"),
                    auth.memo,
                    auth.ord,
                    auth.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", auth.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.updatedAt).as("updatedAtLabel"),
                    auth.createdId,
                    ConvertUtils.getParseUserNm(auth.createdId).as("createdIdLabel"),
                    auth.updatedId,
                    ConvertUtils.getParseUserNm(auth.updatedId).as("updatedIdLabel")
                )
            )
            .from(auth)
            .where(
                authRoleEq(authParam.authRole),
                useYnEq(authParam.useYn),
                authNmLike(authParam.authNm),
                authValLike(authParam.authVal)
            )
            .orderBy(auth.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(auth.count())
            .from(auth)
            .where(
                authRoleEq(authParam.authRole),
                useYnEq(authParam.useYn),
                authNmLike(authParam.authNm),
                authValLike(authParam.authVal)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /* 메소드명 : findAuthBy
     * 기능 : 권한 조회
     * 파라미터 : id
     */
    public AuthDto findAuthBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(AuthDto.class,
                    auth.id.as("authId"),
                    auth.authNm,
                    auth.authVal,
                    auth.authRole,
                    ConvertUtils.getParseCodeNm("AUTH_ROLE", auth.authRole).as("authRoleLabel"),
                    auth.memo,
                    auth.ord,
                    auth.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", auth.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.updatedAt).as("updatedAtLabel"),
                    auth.createdId,
                    ConvertUtils.getParseUserNm(auth.createdId).as("createdIdLabel"),
                    auth.updatedId,
                    ConvertUtils.getParseUserNm(auth.updatedId).as("updatedIdLabel")
                )
            )
            .from(auth)
            .where(
                auth.id.eq(id)
            )
            .fetchOne();
    }


    /************************* 조건절 ***************************/

    private BooleanExpression authRoleEq(String authRole){
        if(authRole == null){
            return null;
        }
        return auth.authRole.eq(authRole);
    }

    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return auth.useYn.eq(useYn);
    }

    private BooleanExpression authNmLike(String authNm){
        if(authNm == null){
            return null;
        }
        return auth.authNm.toUpperCase().contains(authNm.toUpperCase());
    }

    private BooleanExpression authValLike(String authCd){
        if(authCd == null){
            return null;
        }
        return auth.authVal.toUpperCase().contains(authCd.toUpperCase());
    }

}
