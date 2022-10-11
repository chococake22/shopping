package kr.project.shopping.domain.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userIdx;
    private String userId;
    private String password;
    private String name;
    private String phone;
    private String emailYn;
    private String siDo;
    private String siGunGu;
    private String eupMyeonDong;
    private String addrDetail;
    private String authority;
}
