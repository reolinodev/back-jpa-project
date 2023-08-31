package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class MainUserDto {
    public Long userId;

    public String loginId;

    public Long authId;

    public String userNm;

    public String authRole;
}
