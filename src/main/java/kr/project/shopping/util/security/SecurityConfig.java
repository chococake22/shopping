package kr.project.shopping.util.security;

import kr.project.shopping.service.user.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationProvider authenticationProvider;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;
    private final UserOAuth2Service userOAuth2Service;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;


    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .authorizeRequests()
//                .antMatchers("/board/**").authenticated()
//                .antMatchers("/user/**").authenticated()
                .antMatchers("/user/signup", "/login").anonymous()
                .anyRequest().permitAll();

        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("userId")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http
                .oauth2Login()  // oauth2 로그인 처리하는 메소드
                .loginPage("/login")    // 설정을 안하면 그냥 일반 페이지로 이동한다.
//                .successHandler(oAuth2AuthenticationSuccessHandler)
                // 로그인이 성공하면 해당 유저의 정보를 들고 userOAuth2Service에서 후처리를 해주겠다는 의미이다.
                .userInfoEndpoint().userService(userOAuth2Service);

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .httpBasic().disable();

        http
                .exceptionHandling()
                .accessDeniedPage("/WEB-INF/views/error/error.jsp");

        http
                .headers()
                .frameOptions().sameOrigin();

        return http.build();
    }





}
