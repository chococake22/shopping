package kr.project.shopping.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private Long boardIdx;
    private String title;
    private String content;
    private Long regIdx;
    private String regDt;
    private String modDt;
    private String boardType;

}
