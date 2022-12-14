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
                .oauth2Login()  // oauth2 ????????? ???????????? ?????????
                .loginPage("/login")    // ????????? ????????? ?????? ?????? ???????????? ????????????.
//                .successHandler(oAuth2AuthenticationSuccessHandler)
                // ???????????? ???????????? ?????? ????????? ????????? ?????? userOAuth2Service?????? ???????????? ??????????????? ????????????.
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
