package com.back.repository.sample;

import static com.back.domain.sample.QDept.dept;
import static com.back.domain.sample.QUser.user;

import com.back.domain.sample.dto.UserDto;
import com.back.domain.sample.params.UserParam;
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

    /* 메소드명 : searchAll
     * 기능 : 페이징 없는 전체 조회
     * 파라미터 : UserParam
     */
    public List<UserDto> searchAll(UserParam userParam) {
        return queryFactory
            .select(
                Projections.bean(UserDto.class,
                    user.id.as("userId"),
                    user.loginId,
                    user.userNm,
                    dept.deptCd,
                    dept.deptNm,
                    user.telNo,
                    user.useYn,
                    user.loginFailCnt
                )
            )
            .from(user)
            .leftJoin(user.dept, dept)
            .where(
                loginIdLike(userParam.loginId),
                useYnEq(userParam.useYn),
                telNoLike(userParam.telNo)
            )
            .orderBy(user.createdAt.desc())
            .fetch();
    }

    /* 메소드명 : searchWithPaging
     * 기능 : 페이징 처리된 조회
     * 파라미터 : UserParam
     */
    public Page<UserDto> searchWithPaging(UserParam userParam, Pageable pageable) {
        List<UserDto> content = queryFactory
            .select(
                Projections.bean(UserDto.class,
                    user.id.as("userId"),
                    user.loginId,
                    user.userNm,
                    dept.deptCd,
                    dept.deptNm,
                    user.telNo,
                    user.useYn,
                    user.loginFailCnt
                )
            )
            .from(user)
            .leftJoin(user.dept, dept)
            .where(
                loginIdLike(userParam.loginId),
                useYnEq(userParam.useYn),
                telNoLike(userParam.telNo)
            )
            .orderBy(user.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(user.count())
            .from(user)
            .leftJoin(user.dept, dept)
            .where(
                loginIdLike(userParam.loginId),
                useYnEq(userParam.useYn),
                telNoLike(userParam.telNo)
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
                    dept.deptCd,
                    dept.deptNm,
                    user.telNo,
                    user.useYn,
                    user.loginFailCnt
                )
            )
            .from(user)
            .leftJoin(user.dept, dept)
            .where(
                user.id.eq(id)
            )
            .fetchOne();
    }

    /************************* 조건절 ***************************/

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

    private BooleanExpression telNoLike(String telNo){
        if(telNo == null){
            return null;
        }
        return user.telNo.contains(telNo);
    }
}
