package com.back.domain.sample.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class UserDto {
    private Long userId;
    private String loginId;
    private String userNm;
    private String telNo;
    private String deptNm;
    private String deptCd;
    private String useYn;
    public int loginFailCnt;

    @QueryProjection
    public UserDto(Long userId, String loginId, String userNm, String telNo, String deptNm, String deptCd, String useYn, int loginFailCnt){
        this.userId = userId;
        this.loginId = loginId;
        this.userNm = userNm;
        this.telNo = telNo;
        this.deptNm = deptNm;
        this.deptCd = deptCd;
        this.useYn = useYn;
        this.loginFailCnt = loginFailCnt;
    }
}
