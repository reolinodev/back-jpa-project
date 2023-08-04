package com.back.domain.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode()
public class MenuAuthParam {

    //메뉴아이디
    public Long menuId;

    //수정된 코드 Row
    public MenuAuthRow[] updatedRows;

    //등록아이디
    public Long createdId;

    //수정아이디
    public Long updatedId;

}
