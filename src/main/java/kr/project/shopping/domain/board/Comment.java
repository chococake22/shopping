package kr.project.shopping.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String commentIdx;
    private String commentContent;
    private Long regIdx;
    private Long boardIdx;
    private String regDt;
    private String modDt;
    private String boardType;

}
