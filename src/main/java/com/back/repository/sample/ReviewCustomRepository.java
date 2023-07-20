package com.back.repository.sample;

import static com.back.domain.sample.QReview.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewCustomRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ReviewCustomRepository(EntityManager em){
        this.entityManager = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    /* 메소드명 : findBookAvgById
     * 기능 : 사용자 조회
     * 파라미터 : id
     */
    public Double findBookAvgById(Long id) {
        return queryFactory
            .select(
                review.score.avg().coalesce(0D)
            )
            .from(review)
            .where(
                review.book.id.eq(id),
                review.useYn.eq("Y")
            )
            .fetchOne();
    }
}
