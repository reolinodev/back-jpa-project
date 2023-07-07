package com.back.repository.sample;

import com.back.domain.sample.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Test
    void deptTest() {
        Dept dept = new Dept();
        dept.deptNm = "경영팀";
        dept.useYn = "Y";

        deptRepository.save(dept);

        System.out.println(deptRepository.findAll());
    }

    @Test
    void deptTest2() {
        System.out.println(deptRepository.findTop1ByUseYn("Y"));
    }



}
