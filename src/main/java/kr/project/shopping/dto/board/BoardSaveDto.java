package kr.project.shopping.dto.board;

import kr.project.shopping.domain.common.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSaveDto {

    private Long regIdx;
    private String boardType;
    private String title;
    private String content;

}
