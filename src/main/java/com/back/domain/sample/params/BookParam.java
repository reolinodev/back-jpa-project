package com.back.domain.sample.params;

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
@Builder
public class BookParam extends Page {

    public String bookNm;

    public String author;

    public String category;

    public String storeDt;

    public String rentalDt;

    public String useYn;

    public String rentalYn;
}
