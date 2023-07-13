package com.back.repository.sample;

import com.back.domain.sample.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository <Dept, Long> {

    int countByDeptCd(String deptCd);

    Page<Dept> findDeptsBy(Pageable pageable);

}

