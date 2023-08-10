package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class MyAuthDto {
    public Long userAuthId;

    public Long userId;

    public Long authId;

    public String authNm;

    public String authVal;

    public String authRole;
}
