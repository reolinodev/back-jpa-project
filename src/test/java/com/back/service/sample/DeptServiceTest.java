package com.back.service.sample;

import com.back.domain.sample.Dept;
import com.back.domain.sample.params.DeptParam;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class DeptServiceTest {

    @Autowired
    private DeptService deptService;

    @Test
    void checkDept() {
        //given
        String deptCd="DEPT0000001";

        //when
        int checkDeptRes = deptService.checkDept(deptCd);
        System.out.println("result = " + checkDeptRes);

        //then
        Assertions.assertEquals(0, checkDeptRes);
    }

    @Test
    void createDept() {
        //given
        DeptParam dept = DeptParam.builder()
            .deptNm("개발본부")
            .deptCd("DEPT00001")
            .upperDeptCd("")
            .useYn("Y")
            .build();

        DeptParam dept2 = DeptParam.builder()
            .deptNm("경영본부")
            .deptCd("DEPT00002")
            .useYn("Y")
            .build();

        DeptParam dept3 = DeptParam.builder()
            .deptNm("개발1팀")
            .deptCd("DEPT00003")
            .upperDeptCd("DEPT00001")
            .useYn("Y")
            .build();

        DeptParam dept4 = DeptParam.builder()
            .deptNm("개발2팀")
            .deptCd("DEPT00004")
            .upperDeptCd("DEPT00001")
            .useYn("Y")
            .build();

        DeptParam dept5 = DeptParam.builder()
            .deptNm("경영팀1")
            .deptCd("DEPT00005")
            .upperDeptCd("DEPT00002")
            .useYn("Y")
            .build();

        DeptParam dept6 = DeptParam.builder()
            .deptNm("경영팀2")
            .deptCd("DEPT00006")
            .upperDeptCd("DEPT00002")
            .useYn("Y")
            .build();

        List<DeptParam> depts = new ArrayList<>();
        depts.add(dept);
        depts.add(dept2);
        depts.add(dept3);
        depts.add(dept4);
        depts.add(dept5);
        depts.add(dept6);

        //when
        List<Dept> createDeptRes = deptService.createDept(depts);
        System.out.println("result = " + createDeptRes);

        //then
        Assertions.assertEquals(6, createDeptRes.size());
    }



    @Test
    void updateDept() {
        //given
        Long deptId=5L;

        DeptParam deptParam = DeptParam.builder()
            .deptNm("개발팀5")
            .upperDeptCd("DEPT00001")
            .useYn("N")
            .build();

        //when
        Dept updateDeptRes = deptService.updateDept(deptParam, deptId);
        System.out.println("result = " + updateDeptRes);

        //then
        Assertions.assertEquals("개발팀5", updateDeptRes.deptNm);
    }

    @Test
    void getDept() {
        //given
        Long deptId=3L;

        //when
        Dept getDeptRes = deptService.getDept(deptId);
        System.out.println("result = " + getDeptRes);

        //then
        Assertions.assertEquals("DEPT00001", getDeptRes.deptCd);
    }

    @Test
    @Transactional
    void getDepts() {
        //given
        DeptParam deptParam = new DeptParam();
        deptParam.size = 10;
        deptParam.page = 1;

        //when
        Page<Dept> depts = deptService.getDepts(deptParam);
        System.out.println("result = " + depts);

        //then
        Assertions.assertEquals(1, depts.getTotalPages());
    }

}
