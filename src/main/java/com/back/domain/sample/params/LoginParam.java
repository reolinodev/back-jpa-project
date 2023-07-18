package com.back.domain.sample.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class LoginParam {

    public String loginId;

    public String userPw;

    public String device;
}
