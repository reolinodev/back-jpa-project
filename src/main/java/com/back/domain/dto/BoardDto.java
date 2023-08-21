package com.back.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class BoardDto {

    public Long boardId;

    public String boardTitle;

    public String boardType;

    public String boardTypeLabel;

    public String memo;

    public String attachYn;

    public String attachYnLabel;

    public String commentYn;

    public String commentYnLabel;

    public String useYn;

    public String useYnLabel;

    public String createdAtLabel;

    public String updatedAtLabel;

    public Long createdId;

    public String createdIdLabel;

    public Long updatedId;

    public String updatedIdLabel;

    public String boardVal;
}
