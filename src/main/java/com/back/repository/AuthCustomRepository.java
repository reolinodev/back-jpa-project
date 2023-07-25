package com.back.repository;

import static com.back.domain.QAuth.auth;

import com.back.domain.dto.AuthDto;
import com.back.domain.params.AuthParam;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AuthCustomRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public AuthCustomRepository(EntityManager em){
        this.entityManager = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    /* 메소드명 : findAllWithPaging
     * 기능 : 페이징 처리된 권한 조회
     * 파라미터 : UserParam
     */
    public Page<AuthDto> findAllWithPaging(AuthParam authParam, Pageable pageable) {
        List<AuthDto> content = queryFactory
            .select(
                Projections.bean(AuthDto.class,
                    auth.id.as("authId"),
                    auth.authNm,
                    auth.authCd,
                    auth.authRole,
                    auth.memo,
                    auth.ord,
                    auth.useYn,
//                    ConvertUtils.getParseCodeNm("USE_YN", user.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.updatedAt).as("updatedAtLabel"),
                    auth.createdId,
                    auth.updatedId
                    //todo 아이디 이름으로 변환하는 함수 추가
                )
            )
            .from(auth)
            .where(
                authRoleEq(authParam.authRole),
                useYnEq(authParam.useYn),
                authNmLike(authParam.authNm),
                authCdLike(authParam.authCd)
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
                authCdLike(authParam.authCd)
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
                    auth.authCd,
                    auth.authRole,
                    auth.memo,
                    auth.ord,
                    auth.useYn,
//                    ConvertUtils.getParseCodeNm("USE_YN", user.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(auth.updatedAt).as("updatedAtLabel"),
                    auth.createdId,
                    auth.updatedId
                    //todo 아이디 이름으로 변환하는 함수 추가
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

    private BooleanExpression authCdLike(String authCd){
        if(authCd == null){
            return null;
        }
        return auth.authCd.toUpperCase().contains(authCd.toUpperCase());
    }

}
