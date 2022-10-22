package kr.project.shopping.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegItemAnswer {

    private Long regItemAnsIdx;
    private String title;
    private String content;
    private Long regIdx;
    private String regDt;
    private Long regItemQuesIdx;

}
