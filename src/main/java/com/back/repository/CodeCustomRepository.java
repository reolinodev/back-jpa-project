package com.back.repository;

import static com.back.domain.QCode.code;

import com.back.domain.dto.CodeDto;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CodeCustomRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public CodeCustomRepository(EntityManager em){
        this.entityManager = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

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
                    ConvertUtils.getParseLocalDateTimeToString(code.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(code.updatedAt).as("updatedAtLabel"),
                    code.createdId,
                    code.updatedId,
                    code.useYn,
                    code.ord
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

    /************************* 조건절 ***************************/

}
