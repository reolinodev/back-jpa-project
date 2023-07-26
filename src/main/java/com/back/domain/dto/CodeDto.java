package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class CodeDto {

    public Long codeId;

    public String codeNm;

    public String codeVal;

    public String memo;

    public String ord;

    public String prnCodeVal;

    public String useYn;

    public String useYnLabel;

    public String createdAtLabel;

    public String updatedAtLabel;

    public Long createdId;

    public String createdIdLabel;

    public Long updatedId;

    public String updatedIdLabel;
}
