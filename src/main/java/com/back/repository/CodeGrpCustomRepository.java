package com.back.repository;

import static com.back.domain.QCodeGrp.codeGrp;

import com.back.domain.dto.CodeGrpDto;
import com.back.domain.params.CodeGrpParam;
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
public class CodeGrpCustomRepository {
    private final JPAQueryFactory queryFactory;


    /* 메소드명 : findAllWithPaging
     * 기능 : 코드 그룹을 조회한다.
     * 파라미터 : CodeGrpParam
     */
    public Page<CodeGrpDto> findAllWithPaging(CodeGrpParam codeGrpParam, Pageable pageable) {
        List<CodeGrpDto> content = queryFactory
            .select(
                Projections.bean(CodeGrpDto.class,
                    codeGrp.id.as("codeGrpId"),
                    codeGrp.codeGrpNm,
                    codeGrp.codeGrpVal,
                    codeGrp.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", codeGrp.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.updatedAt).as("updatedAtLabel"),
                    codeGrp.createdId,
                    ConvertUtils.getParseUserNm(codeGrp.createdId).as("createdIdLabel"),
                    codeGrp.updatedId,
                    ConvertUtils.getParseUserNm(codeGrp.updatedId).as("updatedIdLabel")
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
                    codeGrp.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", codeGrp.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(codeGrp.updatedAt).as("updatedAtLabel"),
                    codeGrp.createdId,
                    ConvertUtils.getParseUserNm(codeGrp.createdId).as("createdIdLabel"),
                    codeGrp.updatedId,
                    ConvertUtils.getParseUserNm(codeGrp.updatedId).as("updatedIdLabel")
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
        if(useYn == null||"".equals(useYn)){
            return null;
        }
        return codeGrp.useYn.eq(useYn);
    }

    private BooleanExpression codeGrpNmLike(String codeGrpNm){
        if(codeGrpNm == null||"".equals(codeGrpNm)){
            return null;
        }
        return codeGrp.codeGrpNm.toUpperCase().contains(codeGrpNm.toUpperCase());
    }

    private BooleanExpression codeGrpValLike(String codeGrpVal){
        if(codeGrpVal == null||"".equals(codeGrpVal)){
            return null;
        }
        return codeGrp.codeGrpVal.toUpperCase().contains(codeGrpVal.toUpperCase());
    }

}
