package kr.project.shopping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveDto {

    private Long userIdx;
    private String userId;
    private String password;
    private String chkPwd;
    private String name;
    private String phone;
    private String addr;
    private String addr1;
    private String addr2;
    private String addr3;
    private String addrDetail;
    private String addrTotal;
    private String emailYn;
    private String authority;


}
