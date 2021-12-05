package com.bakulovas.tta.security;

import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByLogin(username);
        return UserDetailsImpl.fromUserToUserDetails(user);
    }
}
