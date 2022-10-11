package kr.project.shopping.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String commentIdx;
    private String content;
    private Long regIdx;
    private String regDt;
    private String modDt;
    private String boardType;

}
