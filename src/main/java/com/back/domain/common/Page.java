package com.back.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Page {
    public int page = 0;
    public int size = 10;
    public  void setPaging(int page) {
        if(page < 1){
            this.page = 0;
        }else {
            this.page = page - 1;
        }
    }


}
