package com.back.repository;

import static com.back.domain.QCode.code;

import com.back.domain.dto.CodeDto;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CodeCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findCodeBy
     * 기능 : 코드 정보 상세 조회
     * 파라미터 : id
     */
    public List<CodeDto> findCodeBy(Long codeGrpid) {
        return queryFactory
            .select(
                Projections.bean(CodeDto.class,
                    code.id.as("codeId"),
                    code.codeNm,
                    code.codeVal,
                    code.ord,
                    code.memo,
                    code.useYn,
                    code.prnCodeVal,
                    ConvertUtils.getParseCodeNm("USE_YN", code.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(code.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(code.updatedAt).as("updatedAtLabel"),
                    code.createdId,
                    ConvertUtils.getParseUserNm(code.createdId).as("createdIdLabel"),
                    code.updatedId,
                    ConvertUtils.getParseUserNm(code.updatedId).as("updatedIdLabel")
                )
            )
            .from(code)
            .where(
                code.codeGrp.id.eq(codeGrpid)
            )
            .orderBy(code.ord.when("").then("99")
                .otherwise(code.ord).castToNum(Integer.class).asc(), code.createdAt.asc())
            .fetch();
    }


    public List<CodeDto> findCodeByCodeGrpVal(String codeGrpVal) {
        return queryFactory
            .select(
                Projections.bean(CodeDto.class,
                    code.id.as("codeId"),
                    code.codeNm,
                    code.codeVal,
                    code.ord,
                    code.memo,
                    code.useYn,
                    code.prnCodeVal,
                    ConvertUtils.getParseCodeNm("USE_YN", code.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(code.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(code.updatedAt).as("updatedAtLabel"),
                    code.createdId,
                    ConvertUtils.getParseUserNm(code.createdId).as("createdIdLabel"),
                    code.updatedId,
                    ConvertUtils.getParseUserNm(code.updatedId).as("updatedIdLabel")
                )
            )
            .from(code)
            .where(
                code.codeGrp.codeGrpVal.eq(codeGrpVal),
                code.useYn.eq("Y")
            )
            .orderBy(code.ord.when("").then("99")
                .otherwise(code.ord).castToNum(Integer.class).asc(), code.createdAt.asc())
            .fetch();
    }

    /************************* 조건절 ***************************/

}
