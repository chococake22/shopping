package kr.project.shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDetailVo {

    private Long commentIdx;
    private String commentContent;
    private String writer;
    private String regDt;

}
