package com.back.repository;

import static com.back.domain.QQna.qna;
import com.back.domain.dto.QnaDto;
import com.back.domain.params.QnaParam;
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
public class QnaCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllWithPaging
     * 기능 : 페이징 처리된 Qna 조회
     * 파라미터 : QnaParam
     */
    public Page<QnaDto> findAllWithPaging(QnaParam qnaParam, Pageable pageable) {
        List<QnaDto> content = queryFactory
            .select(
                Projections.bean(QnaDto.class,
                    qna.id.as("qnaId"),
                    qna.board.id.as("boardId"),
                    qna.qnaTitle,
                    qna.board.boardTitle.as("boardTitle"),
                    qna.board.boardType.as("boardType"),
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", qna.board.boardType).as("boardTypeLabel"),
                    qna.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", qna.useYn).as("useYnLabel"),
                    qna.hiddenYn,
                    ConvertUtils.getParseCodeNm("HIDDEN_YN", qna.hiddenYn).as("hiddenYnLabel"),
                    qna.responseYn,
                    ConvertUtils.getParseCodeNm("RESPONSE_YN", qna.responseYn).as("responseLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.updatedAt).as("updatedAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.responseAt).as("responseAtLabel"),
                    qna.createdId,
                    ConvertUtils.getParseUserNm(qna.createdId).as("createdIdLabel"),
                    qna.updatedId,
                    ConvertUtils.getParseUserNm(qna.updatedId).as("updatedIdLabel"),
                    qna.responseId,
                    ConvertUtils.getParseUserNm(qna.responseId).as("responseIdLabel"),
                    qna.viewCnt
                )
            )
            .from(qna)
            .where(
                useYnEq(qnaParam.useYn),
                boardIdEq(qnaParam.boardId),
                qnaTitleLike(qnaParam.qnaTitle),
                createdNm(qnaParam.createdNm),
                createdAtBetween(qnaParam.startDate, qnaParam.endDate)
            )
            .orderBy(qna.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(qna.count())
            .from(qna)
            .where(
                useYnEq(qnaParam.useYn),
                boardIdEq(qnaParam.boardId),
                qnaTitleLike(qnaParam.qnaTitle),
                createdNm(qnaParam.createdNm),
                createdAtBetween(qnaParam.startDate, qnaParam.endDate)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /* 메소드명 : findQnaBy
     * 기능 : Qna 조회
     * 파라미터 : id
     */
    public QnaDto findQnaBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(QnaDto.class,
                    qna.id.as("qnaId"),
                    qna.board.id.as("boardId"),
                    qna.qnaTitle,
                    qna.questions,
                    qna.mainText,
                    qna.board.boardTitle.as("boardTitle"),
                    qna.board.boardType.as("boardType"),
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", qna.board.boardType).as("boardTypeLabel"),
                    qna.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", qna.useYn).as("useYnLabel"),
                    qna.hiddenYn,
                    ConvertUtils.getParseCodeNm("HIDDEN_YN", qna.hiddenYn).as("hiddenYnLabel"),
                    qna.responseYn,
                    ConvertUtils.getParseCodeNm("RESPONSE_YN", qna.responseYn).as("responseLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.updatedAt).as("updatedAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.responseAt).as("responseAtLabel"),
                    qna.createdId,
                    ConvertUtils.getParseUserNm(qna.createdId).as("createdIdLabel"),
                    qna.updatedId,
                    ConvertUtils.getParseUserNm(qna.updatedId).as("updatedIdLabel"),
                    qna.responseId,
                    ConvertUtils.getParseUserNm(qna.responseId).as("responseIdLabel"),
                    qna.viewCnt
                )
            )
            .from(qna)
            .where(
                qna.id.eq(id)
            )
            .fetchOne();
    }


    /************************* 조건절 ***************************/
    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return qna.useYn.eq(useYn);
    }

    private BooleanExpression boardIdEq(Long boardId){
        if(boardId == null){
            return null;
        }
        return qna.board.id.eq(boardId);
    }

    private BooleanExpression qnaTitleLike(String qnaTitle){
        if(qnaTitle == null){
            return null;
        }
        return qna.qnaTitle.toUpperCase().contains(qnaTitle.toUpperCase());
    }

    private BooleanExpression createdAtBetween(String startDate, String endDate){
        if(startDate == null){
            return null;
        }
        return  ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(qna.createdAt).between(startDate, endDate);
    }

    private BooleanExpression createdNm(String createdNm){
        if(createdNm == null){
            return null;
        }
        return ConvertUtils.getParseUserNm(qna.createdId).toUpperCase().contains(createdNm.toUpperCase());
    }

}
