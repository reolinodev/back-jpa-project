package com.back.domain.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RentalDto extends Rental {
    public Long userId;
    public Long bookId;

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
