package kr.project.shopping.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListVo {

    private Long commentIdx;
    private String commentContent;
    private String writer;
    private String regDt;

}
