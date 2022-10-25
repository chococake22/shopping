package kr.project.shopping.dto.board;

import kr.project.shopping.domain.common.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSearchDto extends Page {

    private String boardType;
    private String searchType;

}
