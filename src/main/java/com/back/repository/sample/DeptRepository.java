package com.back.repository.sample;

import com.back.domain.sample.Dept;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository <Dept, Long> {

    int countByDeptCd(String deptCd);

//    void saveAll(List<Dept> depts);
}

