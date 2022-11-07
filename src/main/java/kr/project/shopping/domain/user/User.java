package kr.project.shopping.domain.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails, OAuth2User {

    private Long userIdx;
    private String userId;
    private String password;
    private String name;
    private String phone;
    private String emailYn;
    private String addr1;
    private String addr2;
    private String addr3;
    private String addrDetail;
    private String addrTotal;
    private String authority;

    private Map<String, Object> attributes;
    private String provider;
    private String providerId;
    private String loginId;
    private String nickname;
    private String email;

    public User(String userId, Map<String, Object> attributes) {
        this.userId = userId;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername() {
        return this.userId;
    }
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
