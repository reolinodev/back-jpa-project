package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class AuthUrlDto {
    public Long menuId;

    public int menuLv;

    public Long prnMenuId;

    public String loginId;

    public String url;

    public String prnMenuNm;
}
