package kr.project.shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailVo {

    private Long boardIdx;
    private String title;
    private String content;
    private String writer;
    private String regDt;
    private String modDt;
    private String boardType;
    private int click;
    private int likeCount;

}
