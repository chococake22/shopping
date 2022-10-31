package kr.project.shopping.vo.board;

import kr.project.shopping.domain.common.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListVo extends Page {

    private Long boardIdx;
    private String title;
    private String writer;
    private String regDt;
    private String boardType;
    private int click;
    private int likeCount;
    private Long commentCount;

}
