package kr.project.shopping.util.security;

import kr.project.shopping.domain.user.User;
import kr.project.shopping.service.user.UserServiceLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceLoad userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName(); // 로그인한 이메일 받아온다.
        String password = (String) authentication.getCredentials(); // 로그인한

        System.out.println("CustomAuthenticationProvider : " + userId);

        User user = userService.loadUserByUsername(userId);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않아요.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new UsernamePasswordAuthenticationToken(userId, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 위 메소드에서 return 값으로 UsernamePasswordAuthenticationToken이 들어올 때만 가능하도록 설정
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
