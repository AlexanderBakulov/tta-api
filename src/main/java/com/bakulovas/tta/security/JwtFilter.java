package com.bakulovas.tta.security;

import com.bakulovas.tta.entity.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.bakulovas.tta.config.StaticData.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

@Component
@Log
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("do filter...");

        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        log.info("TOKEN " + token);
        if (token != null) {
            User user = jwtProvider.getUserFromToken(token);
            log.info("USER " + user.getLogin() + " " + user.getRole().getName());
            UserDetailsImpl userDetails = UserDetailsImpl.fromUserToUserDetails(user);
            log.info("USER_DETAILS " + userDetails.getUsername() + " " + userDetails.getAuthorities().toString());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            log.info(auth.toString());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
