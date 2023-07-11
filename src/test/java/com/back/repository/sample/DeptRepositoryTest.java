package com.back.repository.sample;

import com.back.domain.sample.Dept;
import com.back.domain.sample.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void deptTest() {
        Dept dept = new Dept();
        dept.deptNm = "개발팀";
        dept.useYn = "Y";
        dept.deptCd = "DPT0000002";

        deptRepository.save(dept);

        System.out.println(deptRepository.findAll());
    }

//    @Test
//    void deptTest2() {
//        List<User> users = userRepository.findByDeptCd(
//            deptRepository.findTop1ByUseYn("Y").deptCd
//        );
//        System.out.println("users : " +users);
//    }
}
