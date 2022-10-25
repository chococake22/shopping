package kr.project.shopping.service.user;

import kr.project.shopping.domain.user.User;
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

        User user = userService.SELECT_USER_BY_USERID(userId);

        if (user == null) {
            throw new UsernameNotFoundException("없는 회원입니다.");
        }

        System.out.println("loadUserByUsername : " + userId);

        return user;
    }


}
