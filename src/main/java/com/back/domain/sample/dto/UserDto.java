package com.back.domain.sample.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class UserDto {
    public Long userId;

    public String loginId;

    public String userNm;

    public String telNo;

    public String deptNm;

    public String deptCd;

    public String useYn;

    public String useYnNm;

    public int loginFailCnt;

    public String createdAt;

    public String updatedAt;

}
