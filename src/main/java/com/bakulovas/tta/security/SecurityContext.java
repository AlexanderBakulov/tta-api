package com.bakulovas.tta.security;

import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContext {

    public String getUsernameFromSecurityContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            throw new ServerException(ServerError.USER_NOT_AUTHENTICATED);
        }
        Object obj = auth.getPrincipal();
        if(obj == null) {
            throw new ServerException(ServerError.USER_NOT_AUTHENTICATED);
        }
        return ((UserDetailsImpl) obj).getUsername();
    }

}
