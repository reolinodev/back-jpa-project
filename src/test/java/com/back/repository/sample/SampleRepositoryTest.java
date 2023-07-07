package com.back.repository.sample;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import com.back.domain.sample.User;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@SpringBootTest
class SampleRepositoryTest {

   @Autowired
   private SampleRepository sampleRepository;

   // --> JPA Basic

    // 입력 예제 1
   @Test
   void basic1() {

//       User user1 = new User(null,"test1", "test1@gmail.com", "19010101", "1234", "01011115555", "Y", null);
//       User user2 = new User(null,"test2", "test2@gmail.com", "19010101", "1234", "01011116666", "Y", null);
//       User user3 = new User(null,"test3", "test3@gmail.com", "19010101", "1234", "01011116666", "Y", null);
//       User user4 = new User(null,"test4", "test4@gmail.com", "19010101", "1234", "01011116666", "Y", null);
//       User user5 = new User(null,"test5", "test5@gmail.com", "19010101", "1234", "01011116666", "Y", null);

//       sampleRepository.saveAll(Lists.newArrayList(user1, user2, user3, user4, user5));

       List<User> users = sampleRepository.findAll();

       users.forEach(System.out::println);
   }

    @Test
    //입력 후 바로 커밋
    void basic2() {

//        User user = new User(null,"jack39", "jack39@gmail.com", "19830904", "5555", "01011116666");
        User user = new User();
        user.userNm = "jack43";
        user.email="jack43@gmail.com";
        user.userPw ="123456!!A";
        user.birth ="19000101";
        user.telNo = "01011112222";
        user.useYn = "Y";
//        user.se;

        sampleRepository.save(user);

        sampleRepository.flush();;

//        sampleRepository.findAll().forEach(System.out::println);
    }




    @Test
    // 카운트 및 존재 여부 조회 예제
    void basic3() {
        long count = sampleRepository.count();

        boolean exists = sampleRepository.existsById(1L);

        System.out.println(count);

        System.out.println(exists);

    }

    @Test
    // 삭제 예제
    void basic4() {
        sampleRepository.delete(sampleRepository.findById(2L).orElseThrow(RuntimeException::new));
    }


    @Test
    // in 절 사용 삭제 예제
    void basic5() {
        sampleRepository.deleteAllInBatch(sampleRepository.findAllById(Lists.newArrayList(5L, 3L)));
    }

    @Test
    // 페이징 예제
    void basic6() {
        Page<User> users = sampleRepository.findAll(PageRequest.of(0, 3));

        System.out.println("totalElement :" + users.getTotalElements());
        System.out.println("totalPages :" + users.getTotalPages());
        System.out.println("numberofElement : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);
    }

    @Test
    // like 처리 예제
    void basic7() {

        User user = new User();
        user.setEmail("jack@gmail.com");
        user.setUserNm("jack");

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("email", contains())
            .withMatcher("user_nm", contains());

        Example<User> example = Example.of(user, matcher);

        sampleRepository.findAll(example).forEach(System.out::println);

    }

    @Test
    // update 예제
    void basic8() {

        User user = sampleRepository.findById(2L).orElseThrow(RuntimeException::new);
        user.setEmail("test13@hanmail.com");

        sampleRepository.save(user);
    }


    // --> Query Menthod

    @Test
    // 쿼리 메소드 사용 예제
    void query1() {
        System.out.println(sampleRepository.findByUserNm("test"));
    }

    @Test
    //쿼리 메소드 종료 : 동일한 결과
    void query2() {
        System.out.println("findByEmail : " + sampleRepository.findByEmail("jack21@gmail.com"));
        System.out.println("getByEmail : " + sampleRepository.getByEmail("jack21@gmail.com"));
        System.out.println("readByEmail : " + sampleRepository.readByEmail("jack21@gmail.com"));
        System.out.println("queryByEmail : " + sampleRepository.queryByEmail("jack21@gmail.com"));
        System.out.println("searchByEmail : " + sampleRepository.searchByEmail("jack21@gmail.com"));
        System.out.println("streamByEmail : " + sampleRepository.streamByEmail("jack21@gmail.com"));
        System.out.println("findUserByEmail : " + sampleRepository.findUserByEmail("jack21@gmail.com"));
    }


    @Test
    void query3() {
        System.out.println("findFirst2ByUserNm : " + sampleRepository.findFirst2ByUserNm("jack7"));
        System.out.println("findTop2ByUserNm : " + sampleRepository.findTop2ByUserNm("jack7"));
        System.out.println("findLast1ByUserNm : " + sampleRepository.findLast1ByUserNm("jack7"));
    }


    @Test
    void query4() {
        System.out.println("findbyEmailAndUserNm : " + sampleRepository.findByEmailAndUserNm("jack7@gmail.com","jack7"));
        System.out.println("findByEmailOrUserNm : " + sampleRepository.findByEmailOrUserNm("jack72@gmail.com","jack7"));
    }

    @Test
    void query5() {
        System.out.println("findByCreatedAtAfter : " + sampleRepository.findByCreatedAtAfter(
            LocalDateTime.now().minusDays(1L)));

        System.out.println("findByIdAfter : " + sampleRepository.findByIdAfter(7L));

        System.out.println("findByCreatedAtGreaterThan : " + sampleRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));

        System.out.println("findByCreatedAtGreaterThanEqual : " + sampleRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        System.out.println("findByCreatedAtBetween : " + sampleRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));

        System.out.println("findByIdBetween : " + sampleRepository.findByIdBetween(5L, 12L));

        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + sampleRepository.findByIdGreaterThanEqualAndIdLessThanEqual(3L, 10L));
    }

    @Test
    void query6() {
        System.out.println("findByIdIsNotNull : " + sampleRepository.findByIdIsNotNull());

        System.out.println("findByNameIn : " + sampleRepository.findByUserNmIn(Lists.newArrayList("jack10", "jack11")));
   }



    @Test
    void sortingTest1() {
        System.out.println("findTop1ByUserNm : " + sampleRepository.findTop1ByUserNm("reolino"));
        System.out.println("findLast1ByUserNm : " + sampleRepository.findLast1ByUserNm("reolino"));
        System.out.println("findTopByUserNmOrderByIdDesc : " + sampleRepository.findTopByUserNmOrderByIdDesc("reolino"));
        System.out.println("findFirstByUserNmOrderByIdDescEmailAsc : " + sampleRepository.findFirstByUserNmOrderByIdDescEmailAsc("reolino"));
        System.out.println("findFirstByUserNmWithSortParam : " + sampleRepository.findFirstByUserNm("reolino", Sort.by(Order.desc("Id"), Order.asc("email"))));
    }


    @Test
    void pagingTest1() {

        System.out.println("findByEmailWithPaging : " + sampleRepository.findByEmail("jack7@gmail.com", PageRequest.of(1,1, Sort.by(Order.desc("Id")))));

    }


    @Test
    void queryTest() {
        System.out.println(sampleRepository.findRowRecord().get("user_id"));

    }

}
