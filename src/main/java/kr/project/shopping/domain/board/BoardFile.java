package kr.project.shopping.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFile {

    private Long boardFileIdx;
    private String fileName;
    private String filePath;
    private Long regIdx;
    private String regDt;
    private Long boardIdx;


}
