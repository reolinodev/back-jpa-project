package com.back.repository;

import static com.back.domain.QCodeGrp.codeGrp;
import static com.back.domain.QUser.user;
import com.back.domain.dto.CodeGrpDto;
import com.back.domain.dto.LoginDto;
import com.back.domain.params.CodeGrpParam;
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
public class CodeGrpCustomRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public CodeGrpCustomRepository(EntityManager em){
        this.entityManager = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    /* 메소드명 : findAllWithPaging
     * 기능 : 코드 그룹을 조회한다.
     * 파라미터 : UserParam
     */
    public Page<CodeGrpDto> findAllWithPaging(CodeGrpParam codeGrpParam, Pageable pageable) {
        List<CodeGrpDto> content = queryFactory
            .select(
                Projections.bean(CodeGrpDto.class,
                    codeGrp.id.as("codeGrpId"),
                    codeGrp.codeGrpNm,
                    codeGrp.codeGrpVal,
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.updatedAt).as("updatedAtLabel"),
                    codeGrp.createdId,
                    codeGrp.updatedId,
                    codeGrp.useYn
                )
            )
            .from(codeGrp)
            .where(
                useYnEq(codeGrpParam.useYn),
                codeGrpNmLike(codeGrpParam.codeGrpNm),
                codeGrpValLike(codeGrpParam.codeGrpVal)
            )
            .orderBy(codeGrp.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(codeGrp.count())
            .from(codeGrp)
            .where(
                useYnEq(codeGrpParam.useYn),
                codeGrpNmLike(codeGrpParam.codeGrpNm),
                codeGrpValLike(codeGrpParam.codeGrpVal)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }


    /* 메소드명 : findCodeGrpBy
     * 기능 : 코드그룹 정보 상세 조회
     * 파라미터 : id
     */
    public CodeGrpDto findCodeGrpBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(CodeGrpDto.class,
                    codeGrp.id.as("codeGrpId"),
                    codeGrp.codeGrpNm,
                    codeGrp.codeGrpVal,
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.updatedAt).as("updatedAtLabel"),
                    codeGrp.createdId,
                    codeGrp.updatedId,
                    codeGrp.useYn
                )
            )
            .from(codeGrp)
            .where(
                codeGrp.id.eq(id)
            )
            .fetchOne();
    }

    /************************* 조건절 ***************************/


    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return codeGrp.useYn.eq(useYn);
    }

    private BooleanExpression codeGrpNmLike(String codeGrpNm){
        if(codeGrpNm == null){
            return null;
        }
        return codeGrp.codeGrpNm.toUpperCase().contains(codeGrpNm.toUpperCase());
    }

    private BooleanExpression codeGrpValLike(String codeGrpVal){
        if(codeGrpVal == null){
            return null;
        }
        return codeGrp.codeGrpVal.toUpperCase().contains(codeGrpVal.toUpperCase());
    }

}
