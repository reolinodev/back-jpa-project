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
public class CodeGrpParam extends Page {

    public String codeGrpNm;

    public String codeGrpValue;

    public String codeGrpDetail;

    public String useYn;
}
