package kr.project.shopping.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyNoteListVo {

    private Long regItemBuyNoteIdx;
    private Long regIdx;
    private String regDt;
    private String modDt;
    private String writer;
    private String title;
    private String content;
    private String score;
    private Long regItemIdx;

}
