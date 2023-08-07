package com.back.repository;

import static com.back.domain.QPost.post;

import com.back.domain.dto.PostDto;
import com.back.domain.params.PostParam;
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
public class PostCustomRepository {
    private final JPAQueryFactory queryFactory;

    /* 메소드명 : findAllWithPaging
     * 기능 : 페이징 처리된 게시글 조회
     * 파라미터 : PostParam
     */
    public Page<PostDto> findAllWithPaging(PostParam postParam, Pageable pageable) {
        List<PostDto> content = queryFactory
            .select(
                Projections.bean(PostDto.class,
                    post.id.as("postId"),
                    post.board.id.as("boardId"),
                    post.postTitle,
                    post.board.boardTitle.as("boardTitle"),
                    post.board.boardType.as("boardType"),
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", post.board.boardType).as("boardTypeLabel"),
                    post.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", post.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(post.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(post.updatedAt).as("updatedAtLabel"),
                    post.createdId,
                    ConvertUtils.getParseUserNm(post.createdId).as("createdIdLabel"),
                    post.updatedId,
                    ConvertUtils.getParseUserNm(post.updatedId).as("updatedIdLabel"),
                    post.viewCnt
                )
            )
            .from(post)
            .where(
                useYnEq(postParam.useYn),
                boardIdEq(postParam.boardId),
                postTitleLike(postParam.postTitle),
                createdNm(postParam.createdNm),
                createdAtBetween(postParam.startDate, postParam.endDate)
            )
            .orderBy(post.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(post.count())
            .from(post)
            .where(
                useYnEq(postParam.useYn),
                boardIdEq(postParam.boardId),
                postTitleLike(postParam.postTitle),
                createdNm(postParam.createdNm),
                createdAtBetween(postParam.startDate, postParam.endDate)
            )
            .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    /* 메소드명 : findAuthBy
     * 기능 : 권한 조회
     * 파라미터 : id
     */
    public PostDto findPostBy(Long id) {
        return queryFactory
            .select(
                Projections.bean(PostDto.class,
                    post.id.as("postId"),
                    post.board.id.as("boardId"),
                    post.postTitle,
                    post.mainText,
                    post.board.boardTitle.as("boardTitle"),
                    post.board.boardType.as("boardType"),
                    ConvertUtils.getParseCodeNm("BOARD_TYPE", post.board.boardType).as("boardTypeLabel"),
                    post.useYn,
                    ConvertUtils.getParseCodeNm("USE_YN", post.useYn).as("useYnLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(post.createdAt).as("createdAtLabel"),
                    ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(post.updatedAt).as("updatedAtLabel"),
                    post.createdId,
                    ConvertUtils.getParseUserNm(post.createdId).as("createdIdLabel"),
                    post.updatedId,
                    ConvertUtils.getParseUserNm(post.updatedId).as("updatedIdLabel"),
                    post.viewCnt
                )
            )
            .from(post)
            .where(
                post.id.eq(id)
            )
            .fetchOne();
    }


    /************************* 조건절 ***************************/
    private BooleanExpression useYnEq(String useYn){
        if(useYn == null){
            return null;
        }
        return post.useYn.eq(useYn);
    }

    private BooleanExpression boardIdEq(Long boardId){
        if(boardId == null){
            return null;
        }
        return post.board.id.eq(boardId);
    }

    private BooleanExpression postTitleLike(String postTitle){
        if(postTitle == null){
            return null;
        }
        return post.postTitle.toUpperCase().contains(postTitle.toUpperCase());
    }

    private BooleanExpression createdAtBetween(String startDate, String endDate){
        if(startDate == null){
            return null;
        }
        return  ConvertUtils.getParseLocalDateTimeToStringYYYYMMDD(post.createdAt).between(startDate, endDate);
    }

    private BooleanExpression createdNm(String createdNm){
        if(createdNm == null){
            return null;
        }
        return ConvertUtils.getParseUserNm(post.createdId).toUpperCase().contains(createdNm.toUpperCase());
    }

}
