package kr.project.shopping.mapper;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.user.PwdChangeDto;
import kr.project.shopping.dto.user.UserSelectDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    Long INSERT_USER(User user);

    int IF_USER_EXIST(String userId);

    User SELECT_USER_BY_USERID(UserSelectDto dto);

    void UPDATE_PWD(PwdChangeDto dto);
}