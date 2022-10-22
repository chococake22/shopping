package kr.project.shopping.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegItem {

    private Long RegItemIdx;
    private String itemName;
    private int itemCount;
    private int itemPrice;
    private String itemInfo;
    private String itemType;
    private String regDt;
    private Long regIdx;
    private String avgScore;

}
