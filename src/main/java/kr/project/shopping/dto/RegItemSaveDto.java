package kr.project.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegItemSaveDto {

    private Long regItemIdx;
    private Long regIdx;
    private String itemInfo;
    private String itemType;
    private int itemCount;
    private String itemName;
    private int itemPrice;




}
