package kr.project.shopping.dto.shop;

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
public class BuyNoteSaveDto {

    private Long regItembuyNoteIdx;
    private String title;
    private String content;
    private String score;
    private Long regItemIdx;
    private Long regIdx;
    private List<MultipartFile> files;

}
