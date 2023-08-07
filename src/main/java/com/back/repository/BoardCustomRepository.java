package com.back.repository;

import static com.back.domain.QBoard.board;

import com.back.domain.dto.BoardDto;
import com.back.domain.params.BoardParam;
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
public class BoardCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllWithPaging
     * 기능 : 게시판 페이징 처리된 조회
     * 파라미터 : boardParam
     */
    public Page<BoardDto> findAllWithPaging(BoardParam boardParam, Pageable pageable) {
        List<BoardDto> content = queryFactory
            .select(
                Projections.bean(BoardDto.class,
                    board.id.as("boardId"),
                    board.boardTitle,
                    board.boardType,
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", board.boardType).as("boardTypeLabel"),
                    board.memo,
                    board.attachYn,
                    ConvertUtils.getParseCodeNm("ATTACH_YN", board.attachYn).as("attachYnLabel"),
                    board.commentYn,
                    ConvertUtils.getParseCodeNm("COMMENT_YN", board.commentYn).as("commentYnLabel"),
                    board.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", board.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(board.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(board.updatedAt).as("updatedAtLabel"),
                    board.createdId,
                    ConvertUtils.getParseUserNm(board.createdId).as("createdIdLabel"),
                    board.updatedId,
                    ConvertUtils.getParseUserNm(board.updatedId).as("updatedIdLabel")
                )
            )
            .from(board)
            .where(
                boardTypeEq(boardParam.boardType),
                useYnEq(boardParam.useYn),
                boardTitleLike(boardParam.boardTitle)
            )
            .orderBy(board.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(board.count())
            .from(board)
            .where(
                boardTypeEq(boardParam.boardType),
                useYnEq(boardParam.useYn),
                boardTitleLike(boardParam.boardTitle)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /* 메소드명 : findBoardBy
     * 기능 : 게시판 상세조회
     * 파라미터 : id
     */
    public BoardDto findBoardBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(BoardDto.class,
                    board.id.as("boardId"),
                    board.boardTitle,
                    board.boardType,
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", board.boardType).as("boardTypeLabel"),
                    board.memo,
                    board.attachYn,
                    ConvertUtils.getParseCodeNm("ATTACH_YN", board.attachYn).as("attachYnLabel"),
                    board.commentYn,
                    ConvertUtils.getParseCodeNm("COMMENT_YN", board.commentYn).as("commentYnLabel"),
                    board.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", board.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(board.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(board.updatedAt).as("updatedAtLabel"),
                    board.createdId,
                    ConvertUtils.getParseUserNm(board.createdId).as("createdIdLabel"),
                    board.updatedId,
                    ConvertUtils.getParseUserNm(board.updatedId).as("updatedIdLabel")
                )
            )
            .from(board)
            .where(
                board.id.eq(id)
            )
            .fetchOne();
    }


    /************************* 조건절 ***************************/

    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return board.useYn.eq(useYn);
    }

    private BooleanExpression boardTypeEq(String boardType){
        if(boardType == null){
            return null;
        }
        return board.boardType.eq(boardType);
    }

    private BooleanExpression boardTitleLike(String boardTitle){
        if(boardTitle == null){
            return null;
        }
        return board.boardTitle.toUpperCase().contains(boardTitle.toUpperCase());
    }

}
