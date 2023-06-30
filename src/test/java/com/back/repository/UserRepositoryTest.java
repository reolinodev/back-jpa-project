package com.back.repository;

import static java.time.LocalDateTime.now;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import com.back.domain.User;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class UserRepositoryTest {

   @Autowired
   private UserRepository userRepository;

   @Test
   void crud() {

       User user1 = new User(null,"jack8", "jack6@gmail.com", "19830903", "3333", "01011115555", now(), now());
       User user2 = new User(null,"jack9", "jack7@gmail.com", "19830904", "4444", "01011116666", now(), now());
       User user3 = new User(null,"jack10", "jack7@gmail.com", "19830904", "4444", "01011116666", now(), now());
       User user4 = new User(null,"jack11", "jack7@gmail.com", "19830904", "4444", "01011116666", now(), now());
       User user5 = new User(null,"jack12", "jack7@gmail.com", "19830904", "4444", "01011116666", now(), now());
       User user6 = new User(null,"jack13", "jack7@gmail.com", "19830904", "4444", "01011116666", now(), now());
       User user7 = new User(null,"jack14", "jack7@gmail.com", "19830904", "4444", "01011116666", now(), now());

       userRepository.saveAll(Lists.newArrayList(user1, user2, user3, user4, user5, user6, user7));

       List<User> users = userRepository.findAll();

       users.forEach(System.out::println);
   }

    @Test
    void crud2() {

        User user = new User(null,"jack5", "jack4@gmail.com", "19830904", "5555", "01011116666", now(), now());

        userRepository.save(user);

        userRepository.flush();;

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud3() {
        long count = userRepository.count();

        boolean exists = userRepository.existsById(1L);

        System.out.println(count);

        System.out.println(exists);

    }

    @Test
    void crud4() {
        userRepository.delete(userRepository.findById(2L).orElseThrow(RuntimeException::new));
    }


    @Test
    void crud5() {
        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(5L, 3L)));
    }

    @Test
    void crud6() {
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));

        System.out.println("totalElement :" + users.getTotalElements());
        System.out.println("totalPages :" + users.getTotalPages());
        System.out.println("numberofElement : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);
    }

    @Test
    void crud7() {


        User user = new User();
        user.setEmail("jack@gmail.com");
        user.setUser_nm("jack");

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("email", contains())
            .withMatcher("user_nm", contains());

        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);

    }

    @Test
    void crud8() {

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("jacka1@gmail.com");

        userRepository.save(user);
    }





}
