package kr.project.shopping.dto;

import kr.project.shopping.domain.common.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRegDto {

    private String boardType;
    private String title;
    private String content;

}
