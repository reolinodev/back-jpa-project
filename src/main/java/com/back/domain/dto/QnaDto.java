package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class QnaDto {
    public Long qnaId;

    public Long boardId;

    public String qnaTitle;

    public String boardTitle;

    public String boardType;

    public String boardTypeLabel;

    public String questions;

    public String mainText;

    public String hiddenYn;

    public String hiddenYnLabel;

    public String responseYn;

    public String responseLabel;

    public String useYn;

    public String useYnLabel;

    public String createdAtLabel;

    public String updatedAtLabel;

    public String responseAtLabel;

    public Long createdId;

    public String createdIdLabel;

    public Long updatedId;

    public String updatedIdLabel;

    public Long responseId;

    public String responseIdLabel;

    public int viewCnt;
}
