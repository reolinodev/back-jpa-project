package com.back.repository;

import com.back.domain.CodeGrp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeGrpRepository extends JpaRepository <CodeGrp, Long> {
    int countByCodeGrpVal(String codeGrpVal);
}

