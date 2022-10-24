package kr.project.shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegItemListVo {

    private Long regItemIdx;
    private Long regIdx;
    private String regDt;
    private String writer;
    private String itemInfo;
    private String itemType;
    private String itemName;
    private int itemCount;
    private int clickCount;
    private String uuidExt;
}
