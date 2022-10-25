package kr.project.shopping.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegItemDetailVo {

    private Long regItemIdx;
    private Long regIdx;
    private String regDt;
    private String writer;
    private int clickCount;
    private String itemInfo;
    private String itemType;
    private String itemName;
    private int itemCount;
    private int itemPrice;
    private String uuidExt;
    private String delivery;
    private String madeCorp;
}
