package com.bakulovas.tta.security;


import com.bakulovas.tta.entity.Division;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private String login;
    private String password;
    private boolean isTempPassword;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private Office office;
    private Division division;
    private Set<String> roles;
    private boolean isExecutor;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserDetailsImpl fromUserToUserDetails(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.login = user.getLogin();
        userDetails.password = user.getPassword();
        //todo user.getRoles()
        userDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        return userDetails;
    }

    public boolean isTempPassword() {
        return isTempPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Office getOffice() {
        return office;
    }

    public Division getDivision() {
        return division;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public boolean isExecutor() {
        return isExecutor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
