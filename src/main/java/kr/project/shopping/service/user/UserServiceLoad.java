package kr.project.shopping.service.user;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.dto.user.UserSelectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceLoad implements UserDetailsService {

    private final UserServiceImpl userService;

    @Override
    public User loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserSelectDto dto = UserSelectDto.builder()
                .userId(userId)
                .provider("normal")
                .build();

        System.out.println(dto);

        User user = userService.getUserInfo(dto.getUserId());

        if (user == null) {
            throw new UsernameNotFoundException("없는 회원입니다.");
        }

        System.out.println("loadUserByUsername : " + userId);

        return user;
    }


}
