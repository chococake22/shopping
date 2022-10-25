package kr.project.shopping.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardUpdateDto {

    private Long boardIdx;
    private String boardType;
    private String title;
    private String content;

}
