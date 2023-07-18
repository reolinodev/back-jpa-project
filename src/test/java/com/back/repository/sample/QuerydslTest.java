package com.back.repository.sample;

import static com.back.domain.sample.QDept.dept;
import static com.back.domain.sample.QReview.review;
import static com.back.domain.sample.QUser.user;

import com.back.domain.sample.User;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class QuerydslTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    public void search() {
        User findUser = queryFactory
            .select(user)
            .from(user)
            .where(user.loginId.eq("test1@gmail.com"))
            .fetchOne();

        System.out.println("res : " + findUser);
    }

    @Test
    public void searchAndParam() {
        User findUser = queryFactory
            .selectFrom(user)
            .where(
                user.loginId.eq("test1@gmail.com"),
                user.telNo.eq("0100000001")
            )
            .fetchOne();

        System.out.println("res : " + findUser);
    }

    @Test
    public void searchAndParam2() {
        User findUser = queryFactory
            .selectFrom(user)
            .where(
                user.loginId.eq("test1@gmail.com") //login_id = 'test1@gmail.com'
                ,user.loginId.ne("test1@gmail.com") //login_id != 'test1@gmail.com'
                ,user.loginId.eq("test1@gmail.com").not() //login_id != 'test1@gmail.com'
                ,user.loginId.isNotNull() //login_id is not null
                ,user.id.in(1L, 3L) //id in (1L, 3L)
                ,user.id.between(1L, 3L) //id between 1L, 3L
                ,user.id.goe(3L) // id >= 3L
                ,user.id.gt(3L) // id > 3L
                ,user.id.loe(3L) // id <= 3L
                ,user.id.lt(3L) // id < 3L
                ,user.loginId.like("test1%") //login_id like 'test1%'
                ,user.loginId.contains("test1") //login_id like '%test1%'
                ,user.loginId.startsWith("test1") //login_id like 'test1%'
                ,user.loginId.isNotNull().and(user.id.in(1L, 3L)) //id in (1L, 3L)
            )
            .fetchOne();

        System.out.println("res : " + findUser);
    }

    @Test
    public void sort() {
        List<User> findUser = queryFactory
            .select(user)
            .from(user)
            .where(
                user.loginId.like("%test%")
            ).orderBy(user.createdAt.desc(), user.loginId.desc().nullsLast())
            .fetch();

        System.out.println("res : " + findUser);
    }

    @Test
    public void paging() {
        int totalSize = queryFactory
            .selectFrom(user)
                .fetch().size();

        System.out.println("res : " + totalSize);
    }


    @Test
    public void aggregation() {
       List<Tuple> result = queryFactory
           .select(
               review.count(),
               review.score.sum(),
               review.score.avg(),
               review.score.max(),
               review.score.min()
           )
           .from(review)
           .fetch();
       Tuple tuple = result.get(0);

        System.out.println("res : " + tuple);
    }


    @Test
    public void group() {
        List<Tuple> result = queryFactory
            .select(
                review.book.bookNm,
                review.score.avg(),
                review.score.max()
            )
            .from(review)
            .groupBy( review.book.bookNm)
            .fetch();

        System.out.println("res : " + result);
    }

    @Test
    public void join() {
        List<Tuple> result = queryFactory
            .select(
                user.loginId, user.userNm, dept.deptNm
            )
            .from(user)
            .leftJoin(user.dept, dept)
            .where(user.id.eq(2L))
            .fetch();

        System.out.println("res : " + result);
    }

    @Test
    public void fetchJoin() {
        List<User> result = queryFactory
            .selectFrom(user)
            .leftJoin(user.dept, dept).fetchJoin()
            .where(user.id.eq(2L))
            .fetch();

        System.out.println("res : " + result);
    }

    @Test
    public void simpleProjection() {
        List<String> result = queryFactory
            .select(user.loginId)
            .from(user)
            .fetch();

        for(String s : result){
            System.out.println("s =" +s);
        }
    }

    @Test
    public void tupleProjection() {
        List<Tuple> result = queryFactory
            .select(user.id,user.loginId, user.dept.deptNm, user.telNo)
            .from(user)
            .fetch();

        for(Tuple tuple : result){
            Long id = tuple.get(user.id);
            String loginId = tuple.get(user.loginId);
            System.out.println("1 <<" +id);
            System.out.println("2 <<" +loginId);
        }
    }

    @Test
    public void findDtoBySetter() {
        List<User> result = queryFactory
            .select(Projections.bean(User.class,
                    user.id,
                    user.loginId,
                    user.dept.deptNm,
                    user.telNo))
            .from(user)
            .fetch();

        for(User user : result){
            System.out.println("1 <<" +user);
        }
    }

    @Test
    public void bulkUpdate() {
        long count = queryFactory
            .update(user)
            .set(user.useYn, "Y")
            .where(user.createdAt.isNotNull())
            .execute();

        entityManager.flush();
        entityManager.clear();;

        System.out.println("1 <<" +count);
    }

    @Test
    public void dynamicQuery() {
        String useYn = "Y";
        String telNo = "2";


        List<User> result = queryFactory
            .select(Projections.bean(User.class,
                user.id,
                user.loginId,
                user.dept.deptNm,
                user.telNo))
            .from(user)
            .fetch();

        for(User user : result){
            System.out.println("1 <<" +user);
        }
    }

}
