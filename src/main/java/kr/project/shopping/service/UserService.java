package kr.project.shopping.service;


import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.BoardRegDto;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.PwdChangeDto;
import kr.project.shopping.dto.UserSaveDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public interface UserService {

    public Long INSERT_USER(UserSaveDto dto);

    public int IF_USER_EXIST(String userId);

    public User SELECT_USER_BY_USERID(String userId);

    public Map<String, Object> UPDATE_PWD(PwdChangeDto dto);

}
