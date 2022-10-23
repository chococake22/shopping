package kr.project.shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegItemDetailVo {

    private Long regItemIdx;
    private Long regIdx;
    private String itemInfo;
    private String itemType;
    private String itemName;
    private int itemCount;
}
