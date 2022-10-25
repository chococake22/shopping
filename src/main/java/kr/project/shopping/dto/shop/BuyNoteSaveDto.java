package kr.project.shopping.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyNoteSaveDto {

    private Long regItembuyNoteIdx;
    private String title;
    private String content;
    private String score;
    private Long regItemIdx;
    private Long regIdx;

}
