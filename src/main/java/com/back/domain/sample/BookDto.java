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
public class BookDto extends Book {
    public int page = 0;
    public int size = 10;
    public void setPageIdx(int page) {
        if(page < 1){
            this.page = 0;
        }else {
            this.page = page - 1;
        }
    }
}
