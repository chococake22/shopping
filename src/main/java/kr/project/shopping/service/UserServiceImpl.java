package kr.project.shopping.service;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.UserSaveDto;
import kr.project.shopping.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long INSERT_USER(UserSaveDto dto) {

        User user = null;

        try {
            if (IF_USER_EXIST(dto.getUserId()) > 0) {
                throw new RuntimeException("이미 존재하는 아이디입니다.");
            }

            if (dto.getEmailYn() == null) {
                dto.setEmailYn("N");
            };

            // 주소 나누기
            String[] addrs = dto.getAddr().split(" ");

            user = User.builder()
                    .userId(dto.getUserId())
                    .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                    .name(dto.getName())
                    .phone(dto.getPhone())
                    .emailYn(dto.getEmailYn())
                    .addr1(addrs[0])
                    .addr2(addrs[1])
                    .addr3(dto.getAddr().substring(dto.getAddr().indexOf(addrs[2]), dto.getAddr().length()))
                    .addrDetail(dto.getAddrDetail())
                    .addrTotal(dto.getAddr() + " " + dto.getAddrDetail())
                    .authority("USER")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("오류가 발생했습니다.");
        }

        return userMapper.INSERT_USER(user);
    }

    public int IF_USER_EXIST(String userId) {
        return userMapper.IF_USER_EXIST(userId);
    }

    @Override
    public User SELECT_USER_BY_USERID(String userId) {
        return userMapper.SELECT_USER_BY_USERID(userId);
    }
}
