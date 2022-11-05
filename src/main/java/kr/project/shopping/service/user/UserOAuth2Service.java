package kr.project.shopping.service.user;

import kr.project.shopping.domain.user.KakaoUserInfo;
import kr.project.shopping.domain.user.OAuthAttributes;
import kr.project.shopping.domain.user.PrincipalDetails;
import kr.project.shopping.dto.user.KakaoUserSaveDto;
import kr.project.shopping.mapper.KakaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserOAuth2Service extends DefaultOAuth2UserService {

    private final KakaoMapper kakaoMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // 액세스 토큰을 통해서 사용자 정보를 받아온다.
    // UserPrincipal내 리턴한다.
    // Oauth 로그인 처리는 이 클래스에서 진행함

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("OAuth2 로그인 시작");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                new DefaultOAuth2UserService();

        // 부모 클래스를 통해서 user 정보를 받아온다.
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 가져온 정보를 kakao 기준에 맞게 설정함.
        OAuth2UserInfo oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String nickname = oAuth2UserInfo.getName();
        String email = oAuth2UserInfo.getEmail();
        String password = bCryptPasswordEncoder.encode("aa");
        String loginId = provider + "_" + providerId;

        System.out.println("provider : " + provider);
        System.out.println("providerId : " + providerId);
        System.out.println("nickname : " + nickname);
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        System.out.println("loginId : " + loginId);

        // DB에서 카카오로 가입된 회원이 있는지 조회하기
        KakaoUserSaveDto user = kakaoMapper.SELECT_USER_BY_EMAIL(email);

        // 없으면 계정 생성
        if (user == null) {
            user = KakaoUserSaveDto.builder()
                    .loginId(loginId)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .build();

            kakaoMapper.INSERT_USER_KAKAO(user);
        }

        // 어느 플랫폼을 통해서 로그인했는지? ex)kakao, google, naver 등
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println("userNameAttributeName : " + userNameAttributeName);
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        System.out.println("attributes : " + attributes);

        System.out.println(registrationId);
        System.out.println("oAuth2User.getAttributes() : " + oAuth2User.getAttributes());

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
