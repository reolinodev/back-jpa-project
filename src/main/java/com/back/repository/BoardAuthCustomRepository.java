package com.back.repository;

import static com.back.domain.QAuth.auth;
import static com.back.domain.QBoardAuth.boardAuth;
import static com.back.domain.QUser.user;

import com.back.domain.BoardAuth;
import com.back.domain.dto.BoardAuthDto;
import com.back.support.ConvertUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardAuthCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllAuthByMenu
     * 기능 : 메뉴별 권한 목록 조회
     * 파라미터 : id
     */
    public List<BoardAuthDto> findAllByBoardId(Long boardId) {
        return queryFactory
            .select(
                Projections.bean(BoardAuthDto.class,
                    boardAuth.board.id.as("boardId"),
                    boardAuth.auth.id.as("authId"),
                    boardAuth.useYn,
                    boardAuth.useYn.coalesce("N").as("useYn"),
                    boardAuth.createdId,
                    ConvertUtils.getParseUserNm(boardAuth.createdId).as("createdIdLabel"),
                    boardAuth.updatedId,
                    ConvertUtils.getParseUserNm(boardAuth.updatedId).as("updatedIdLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(boardAuth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(boardAuth.updatedAt).as("updatedAtLabel")
                )
            )
            .from(boardAuth)
            .where(
                boardAuth.board.id.eq(boardId)
            )
            .fetch();
    }

    /* 메소드명 : findByBoardIdAndAuthId
     * 기능 : 메뉴별 권한 목록 조회
     * 파라미터 : menuId, authId
     */
    public BoardAuth findByBoardIdAndAuthId(Long boardId, Long authId) {
        return queryFactory
            .select(
                Projections.bean(BoardAuth.class,
                    boardAuth.id,
                    boardAuth.board,
                    boardAuth.auth,
                    boardAuth.useYn,
                    boardAuth.createdId,
                    boardAuth.updatedId,
                    boardAuth.createdAt,
                    boardAuth.updatedAt
                )
            )
            .from(boardAuth)
            .where(
                boardAuth.board.id.eq(boardId),
                boardAuth.auth.id.eq(authId)
            )
            .fetchOne();
    }


    /* 메소드명 : countByBoardIdAndAuthId
     * 기능 : 게시글 수정권한 조회
     * 파라미터 : menuId, authId
     */
    public Long countByBoardIdAndAuthId(Long boardId, Long authId) {
        return queryFactory
            .select(
                boardAuth.count()
            )
            .from(boardAuth)
            .where(
                boardAuth.board.id.eq(boardId),
                boardAuth.auth.id.eq(authId)
            )
            .fetchOne();
    }


    /* 메소드명 : findAllAuthByMenu
     * 기능 : 메뉴별 권한 목록 조회
     * 파라미터 : id
     */
    public List<BoardAuthDto> findBoardAuths(Long boardId, String authRole) {
        return queryFactory
            .select(
                Projections.bean(BoardAuthDto.class,
                    auth.id.as("authId"),
                    auth.authNm,
                    boardAuth.useYn,
                    boardAuth.useYn.coalesce("N").as("useYn"),
                    boardAuth.createdId,
                    ConvertUtils.getParseUserNm(boardAuth.createdId).as("createdIdLabel"),
                    boardAuth.updatedId,
                    ConvertUtils.getParseUserNm(boardAuth.updatedId).as("updatedIdLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(boardAuth.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToString(boardAuth.updatedAt).as("updatedAtLabel")
                )
            )
            .from(auth)
            .leftJoin(auth.boardAuths, boardAuth).on(boardAuth.board.id.eq(boardId),auth.authRole.eq(authRole))
            .where(
                auth.useYn.eq("Y")
            )
            .orderBy(auth.ord.when("").then("99")
                .otherwise(auth.ord).castToNum(Integer.class).asc(), auth.createdAt.asc())
            .fetch();
    }

    /************************* 조건절 ***************************/
}
