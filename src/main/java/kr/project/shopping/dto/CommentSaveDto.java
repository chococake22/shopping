package kr.project.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSaveDto {

    private Long boardIdx;
    private Long regIdx;
    private String commentContent;

}
