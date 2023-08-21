package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class FaqDto {
    public Long faqId;

    public Long boardId;

    public String faqTitle;

    public String boardTitle;

    public String mainText;

    public int viewCnt;

    public String boardType;

    public String boardTypeLabel;

    public String useYn;

    public String useYnLabel;

    public String createdAtLabel;

    public String updatedAtLabel;

    public Long createdId;

    public String createdIdLabel;

    public Long updatedId;

    public String updatedIdLabel;
}
