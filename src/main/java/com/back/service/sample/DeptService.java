package com.back.service.sample;

import com.back.domain.sample.Dept;
import com.back.domain.sample.DeptDto;
import com.back.repository.sample.DeptRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptService {

    private final DeptRepository deptRepository;

    /**
     * todo 부서를 전체조회 합니다. (동적 쿼리 변경)
     */
    public Page<Dept> getDepts(DeptDto deptDto) {
        deptDto.setPageIdx(deptDto.page);
        return deptRepository.findDeptsBy(PageRequest.of(deptDto.page,deptDto.size, Sort.by(Order.desc("id"))));
    }
    /**
     * 사용자를 상세 조회 합니다.
     */
    public Dept getDept(long id) {
        return deptRepository.findById(id).get();
    }
////
////
    /**
     * 부서코드가 존재하는지 체크합니다.
     */
    public int checkDept(String deptCd) {
        return deptRepository.countByDeptCd(deptCd);
    }

    /**
     * 부서를 생성합니다.
     */
    public List<Dept> createDept(List<Dept> depts) {
       return deptRepository.saveAll(depts);
    }


    /**
     * 부서를 수정합니다.
     */
    public Dept updateDept(Dept dept, Long id) {
        dept.id = id;
        return deptRepository.save(dept);
    }

}
