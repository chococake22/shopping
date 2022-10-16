package kr.project.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PwdChangeDto {

    private String userId;
    private String beforePwd;
    private String newPwd;
    private String newPwdChk;

}
