package com.back.repository;

import static com.back.domain.QFaq.faq;

import com.back.domain.dto.FaqDto;
import com.back.domain.params.FaqParam;
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
public class FaqCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllWithPaging
     * 기능 : 페이징 처리된 Faq 조회
     * 파라미터 : PostParam
     */
    public Page<FaqDto> findAllWithPaging(FaqParam faqParam, Pageable pageable) {
        List<FaqDto> content = queryFactory
            .select(
                Projections.bean(FaqDto.class,
                    faq.id.as("faqId"),
                    faq.board.id.as("boardId"),
                    faq.faqTitle,
                    faq.board.boardTitle.as("boardTitle"),
                    faq.board.boardType.as("boardType"),
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", faq.board.boardType).as("boardTypeLabel"),
                    faq.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", faq.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(faq.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(faq.updatedAt).as("updatedAtLabel"),
                    faq.createdId,
                    ConvertUtils.getParseUserNm(faq.createdId).as("createdIdLabel"),
                    faq.updatedId,
                    ConvertUtils.getParseUserNm(faq.updatedId).as("updatedIdLabel"),
                    faq.viewCnt
                )
            )
            .from(faq)
            .where(
                useYnEq(faqParam.useYn),
                boardIdEq(faqParam.boardId),
                faqTitleLike(faqParam.faqTitle)
            )
            .orderBy(faq.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(faq.count())
            .from(faq)
            .where(
                useYnEq(faqParam.useYn),
                boardIdEq(faqParam.boardId),
                faqTitleLike(faqParam.faqTitle)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /* 메소드명 : findAuthBy
     * 기능 : 권한 조회
     * 파라미터 : id
     */
    public FaqDto findFaqBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(FaqDto.class,
                    faq.id.as("faqId"),
                    faq.board.id.as("boardId"),
                    faq.faqTitle,
                    faq.mainText,
                    faq.board.boardTitle.as("boardTitle"),
                    faq.board.boardType.as("boardType"),
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", faq.board.boardType).as("boardTypeLabel"),
                    faq.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", faq.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(faq.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(faq.updatedAt).as("updatedAtLabel"),
                    faq.createdId,
                    ConvertUtils.getParseUserNm(faq.createdId).as("createdIdLabel"),
                    faq.updatedId,
                    ConvertUtils.getParseUserNm(faq.updatedId).as("updatedIdLabel"),
                    faq.viewCnt
                )
            )
            .from(faq)
            .where(
                faq.id.eq(id)
            )
            .fetchOne();
    }


    /************************* 조건절 ***************************/
    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return faq.useYn.eq(useYn);
    }

    private BooleanExpression boardIdEq(Long boardId){
        if(boardId == null){
            return null;
        }
        return faq.board.id.eq(boardId);
    }

    private BooleanExpression faqTitleLike(String faqTitle){
        if(faqTitle == null){
            return null;
        }
        return faq.faqTitle.toUpperCase().contains(faqTitle.toUpperCase());
    }

}
