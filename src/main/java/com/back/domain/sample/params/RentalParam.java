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
public class RentalParam extends Page {

    public String rentalDt;

    public String returnDt;

    public String returnYn;

    public Long userId;

    public Long bookId;
}
