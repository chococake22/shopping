package kr.project.shopping.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyNote {

    private Long buyNoteIdx;
    private Long regIdx;
    private String regDt;
    private String modDt;
    private String title;
    private String content;
    private Double score;
    private String regItemIdx;
}
