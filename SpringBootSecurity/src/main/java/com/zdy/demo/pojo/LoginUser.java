package com.zdy.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    public LoginUser(String username, String finalPswd, List<GrantedAuthority> authorities) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        /*try {
            if (Objects.isNull(user)) {
                throw new RuntimeException("user为空");
            }*/
            return user.getPassword();
        /*} catch (RuntimeException e) {
            return e.getMessage();
        }*/
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
