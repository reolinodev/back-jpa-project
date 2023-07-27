package com.back.repository;

import static com.back.domain.QUser.user;
import static com.back.domain.QAuth.auth;
import static com.back.domain.QUserAuth.userAuth;

import com.back.domain.dto.UserAuthDto;
import com.back.domain.dto.UserAuthInputDto;
import com.back.domain.params.UserAuthParam;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllWithPaging
     * 기능 : 권한을 가진 사용자의 정보를 조회
     * 파라미터 : UserAuthParam
     */
    public Page<UserAuthDto> findAllWithPaging(UserAuthParam userAuthParam, Pageable pageable) {
        List<UserAuthDto> content = queryFactory
            .select(
                Projections.bean(UserAuthDto.class,
                    userAuth.id.as("userAuthId"),
                    user.id.as("userId"),
                    auth.id.as("authId"),
                    user.loginId,
                    user.userNm,
                    user.telNo,
                    auth.authVal,
                    auth.authRole,
                    ConvertUtils.getParseCodeNm("AUTH_ROLE", auth.authRole).as("authRoleLabel"),
                    auth.authNm,
                    userAuth.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", userAuth.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(userAuth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(userAuth.updatedAt).as("updatedAtLabel"),
                    userAuth.createdId,
                    ConvertUtils.getParseUserNm(userAuth.createdId).as("createdIdLabel"),
                    userAuth.updatedId,
                    ConvertUtils.getParseUserNm(userAuth.updatedId).as("updatedIdLabel")
                )
            )
            .from(userAuth)
            .where(
                AuthRoleEq(userAuthParam.authRole),
                AuthIdEq(userAuthParam.authId),
                loginIdLike(userAuthParam.loginId),
                userNmLike(userAuthParam.userNm),
                user.useYn.eq("Y"),
                auth.useYn.eq("Y"),
                userAuth.useYn.eq("Y")
            )
            .orderBy(userAuth.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(userAuth.count())
            .from(userAuth)
            .where(
                AuthRoleEq(userAuthParam.authRole),
                AuthIdEq(userAuthParam.authId),
                loginIdLike(userAuthParam.loginId),
                userNmLike(userAuthParam.userNm),
                user.useYn.eq("Y"),
                auth.useYn.eq("Y"),
                userAuth.useYn.eq("Y")
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }


    /* 메소드명 : findAllInputUserAuthsWithPaging
     * 기능 : 해당 권한을 가지지 않는 사용자를 조회
     * 파라미터 : UserAuthParam
     */
    public Page<UserAuthInputDto> findAllInputUserAuthsWithPaging(UserAuthParam userAuthParam, Pageable pageable) {
        List<UserAuthInputDto> content = queryFactory
            .select(
                Projections.bean(UserAuthInputDto.class,
                    user.id.as("userId"),
                    user.loginId,
                    user.userNm,
                    user.telNo
                )
            )
            .from(user)
            .where(
                user.useYn.eq("Y"),
                user.id.notIn(
                    JPAExpressions.select(userAuth.user.id)
                        .from(userAuth)
                        .where(
                            userAuth.auth.id.eq(userAuthParam.authId),
                            userAuth.useYn.eq("Y")
                        )
                ),
                loginIdLike(userAuthParam.loginId),
                userNmLike(userAuthParam.userNm)
            )
            .orderBy(user.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(user.count())
            .from(user)
            .where(
                user.useYn.eq("Y"),
                user.id.notIn(
                    JPAExpressions.select(userAuth.user.id)
                        .from(userAuth)
                        .where(
                            userAuth.auth.id.eq(userAuthParam.authId),
                            userAuth.useYn.eq("Y")
                        )
                ),
                loginIdLike(userAuthParam.loginId),
                userNmLike(userAuthParam.userNm)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /************************* 조건절 ***************************/


    private BooleanExpression AuthRoleEq(String authRole){
        if(authRole == null){
            return null;
        }
        return auth.authRole.eq(authRole);
    }

    private BooleanExpression AuthIdEq(Long authId){
        if(authId == null){
            return null;
        }
        return auth.id.eq(authId);
    }

    private BooleanExpression loginIdLike(String loginId){
        if(loginId == null){
            return null;
        }
        return user.loginId.toUpperCase().contains(loginId.toUpperCase());
    }

    private BooleanExpression userNmLike(String userNm){
        if(userNm == null){
            return null;
        }
        return user.userNm.toUpperCase().contains(userNm.toUpperCase());
    }

}
