package com.back.domain.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User {
    public int page = 0;
    public int size = 10;
    public void setPageIdx(int size, int page) {
        this.page = size * (page - 1);
    }
}
