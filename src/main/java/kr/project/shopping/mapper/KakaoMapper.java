package kr.project.shopping.mapper;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.user.KakaoUserSaveDto;
import kr.project.shopping.dto.user.PwdChangeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KakaoMapper {

    void INSERT_USER_KAKAO(KakaoUserSaveDto dto);

    KakaoUserSaveDto SELECT_USER_BY_EMAIL(String email);

    KakaoUserSaveDto SELECT_USER_BY_LOGINID(String loginId);

    boolean HAS_USER_BY_USERNAME(String username);
}