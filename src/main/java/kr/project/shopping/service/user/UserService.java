package kr.project.shopping.service.user;


import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.user.PwdChangeDto;
import kr.project.shopping.dto.user.UserSaveDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserService {

    public Long INSERT_USER(UserSaveDto dto);

    public int IF_USER_EXIST(String userId);

    public User SELECT_USER_BY_USERID(String userId);

    public Map<String, Object> UPDATE_PWD(PwdChangeDto dto);

}
