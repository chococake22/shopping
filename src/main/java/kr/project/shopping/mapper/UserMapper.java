package kr.project.shopping.mapper;

import kr.project.shopping.domain.board.Board;
import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.BoardSearchDto;
import kr.project.shopping.dto.PwdChangeDto;
import kr.project.shopping.dto.UserSaveDto;
import kr.project.shopping.vo.BoardDetailVo;
import kr.project.shopping.vo.BoardListVo;
import org.apache.ibatis.annotations.Mapper;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    Long INSERT_USER(User user);

    int IF_USER_EXIST(String userId);

    public User SELECT_USER_BY_USERID(String userId);

    void UPDATE_PWD(PwdChangeDto dto);
}