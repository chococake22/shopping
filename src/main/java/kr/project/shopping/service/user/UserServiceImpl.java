package kr.project.shopping.service.user;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.user.PwdChangeDto;
import kr.project.shopping.dto.user.UserSaveDto;
import kr.project.shopping.dto.user.UserSelectDto;
import kr.project.shopping.mapper.UserMapper;
import kr.project.shopping.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CommonUtil commonUtil;

    @Transactional
    public Long INSERT_USER(UserSaveDto dto) {

        User user = null;

        try {

            if (commonUtil.isEmail(dto) == false) {
                throw new RuntimeException("이메일 형식이 맞지 않습니다.");
            }

            if (IF_USER_EXIST(dto.getUserId()) > 0) {
                throw new RuntimeException("이미 존재하는 아이디입니다.");
            }

            // 주소 나누기
            String[] addrs = dto.getAddr().split(" ");

            user = User.builder()
                    .userId(dto.getUserId())
                    .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                    .nickname(dto.getNickname())
                    .phone(dto.getPhone())
                    .emailYn(dto.getEmailYn())
                    .addr1(addrs[0])
                    .addr2(addrs[1])
                    .addr3(dto.getAddr().substring(dto.getAddr().indexOf(addrs[2]), dto.getAddr().length()))
                    .addrDetail(dto.getAddrDetail())
                    .addrTotal(dto.getAddr() + " " + dto.getAddrDetail())
                    .authority("USER")
                    .provider("normal")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userMapper.INSERT_USER(user);
    }

    public int IF_USER_EXIST(String userId) {
        return userMapper.IF_USER_EXIST(userId);
    }

    @Override
    public User SELECT_USER_BY_USERID(UserSelectDto dto) {
        return userMapper.SELECT_USER_BY_USERID(dto);
    }

    @Override
    public Map<String, Object> UPDATE_PWD(PwdChangeDto dto) {

        Map<String, Object> map = new HashMap<>();

        try {
            User user = getUserInfo(dto.getUserId());

            if (!bCryptPasswordEncoder.matches(dto.getBeforePwd(), user.getPassword())) {
                throw new RuntimeException("비밀번호가 틀립니다.");
            }

            if (!dto.getNewPwd().equals(dto.getNewPwdChk())) {
                throw new RuntimeException("두 비밀번호가 다릅니다.");
            }

            dto.setNewPwd(bCryptPasswordEncoder.encode(dto.getNewPwd()));

            userMapper.UPDATE_PWD(dto);
            map.put("msg", "비밀번호가 변경되었습니다.");

        } catch (Exception e) {
            map.put("msg", "실패했습니다.");
        }

        return map;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        final List<GlobalAuthenticationConfigurerAdapter> configurerAdapters = new ArrayList<>();
        configurerAdapters.add(new GlobalAuthenticationConfigurerAdapter() {
            @Override
            public void configure(AuthenticationManagerBuilder auth) throws Exception {
                super.configure(auth);
            }
        });
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 일반 로그인 정보 가져오기
    public User getUserInfo(String userId) {

        UserSelectDto dto = UserSelectDto.builder()
                .userId(userId)
                .provider("normal")
                .build();

        User user = SELECT_USER_BY_USERID(dto);

        return user;

    }

    // 소셜 로그인 정보 가져오기
    public User getUserInfo(String userId, String provider) {

        UserSelectDto dto = UserSelectDto.builder()
                .userId(userId)
                .provider(provider)
                .build();

        User user = SELECT_USER_BY_USERID(dto);

        return user;

    }

}
