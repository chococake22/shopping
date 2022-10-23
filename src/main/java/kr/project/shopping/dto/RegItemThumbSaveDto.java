package kr.project.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegItemThumbSaveDto {

    private String fileName;
    private String filePath;
    private Long regIdx;
    private Long regItemIdx;
    private String uuid;





}
