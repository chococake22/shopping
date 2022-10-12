package kr.project.shopping.dto;

import kr.project.shopping.domain.common.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSearchDto extends Page {

    private String boardType;
    private String searchType;

}
