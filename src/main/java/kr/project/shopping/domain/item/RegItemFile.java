package kr.project.shopping.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegItemFile {

    private Long boardFileIdx;
    private String fileName;
    private String saveName;
    private String filePath;
    private Long regIdx;
    private String modDt;
    private String regDt;
    private Long regItemIdx;
    private String uuid;
    private String uuidExt;

}
