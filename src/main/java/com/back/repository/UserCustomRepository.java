package com.back.repository;


import static com.back.domain.QUser.user;

import com.back.domain.dto.UserDto;
import com.back.domain.params.UserParam;
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
public class UserCustomRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public UserCustomRepository(EntityManager em){
        this.entityManager = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    /* 메소드명 : findAllWithPaging
     * 기능 : 페이징 처리된 조회
     * 파라미터 : UserParam
     */
    public Page<UserDto> findAllWithPaging(UserParam userParam, Pageable pageable) {
        List<UserDto> content = queryFactory
            .select(
                Projections.bean(UserDto.class,
                    user.id.as("userId"),
                    user.loginId,
                    user.userNm,
                    user.telNo,
                    user.useYn,
//                    ConvertUtils.getParseCodeNm("USE_YN", user.useYn).as("useYnVal"),
                    ConvertUtils.getParseLocalDateTimeToString(user.lastLoginAt).as("lastLoginVal"),
                    user.pwInitYn,
                    user.loginFailCnt,
                    ConvertUtils.getParseLocalDateTimeToString(user.createdAt).as("createdAtVal"),
                    ConvertUtils.getParseLocalDateTimeToString(user.updatedAt).as("updatedAtVal")
                )
            )
            .from(user)
            .where(
                loginIdLike(userParam.loginId),
                useYnEq(userParam.useYn),
                userNmLike(userParam.userNm)
            )
            .orderBy(user.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(user.count())
            .from(user)
            .where(
                loginIdLike(userParam.loginId),
                useYnEq(userParam.useYn),
                userNmLike(userParam.userNm)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /* 메소드명 : findUserBy
     * 기능 : 사용자 조회
     * 파라미터 : id
     */
    public UserDto findUserBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(UserDto.class,
                    user.id.as("userId"),
                    user.loginId,
                    user.userNm,
                    user.telNo,
                    user.useYn,
//                    ConvertUtils.getParseCodeNm("USE_YN", user.useYn).as("useYnVal"),
                    ConvertUtils.getParseLocalDateTimeToString(user.lastLoginAt).as("lastLoginVal"),
                    user.pwInitYn,
                    user.loginFailCnt,
                    ConvertUtils.getParseLocalDateTimeToString(user.createdAt).as("createdAtVal"),
                    ConvertUtils.getParseLocalDateTimeToString(user.updatedAt).as("updatedAtVal")
                )
            )
            .from(user)
            .where(
                user.id.eq(id)
            )
            .fetchOne();
    }
//
//    /************************* 조건절 ***************************/
//
    private BooleanExpression loginIdLike(String loginId){
        if(loginId == null){
            return null;
        }
        return user.loginId.contains(loginId);
    }

    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return user.useYn.eq(useYn);
    }

    private BooleanExpression userNmLike(String userNm){
        if(userNm == null){
            return null;
        }
        return user.userNm.contains(userNm);
    }

}
