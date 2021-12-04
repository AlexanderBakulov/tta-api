package com.bakulovas.tta.security;

import com.bakulovas.tta.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put("userId", user.getId());
        claims.put("isActive", user.isActive());
        claims.put("isTempPassword", user.isTempPassword());
        claims.put("officeId", user.getOfficeId());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public User getUserFromToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();
            user.setLogin(body.getSubject());
            user.setId((Integer) body.get("userId"));
            user.setRole((String) body.get("role"));
            user.setActive((boolean) body.get("isActive"));
            user.setTempPassword((boolean) body.get("isTempPassword"));
            user.setOfficeId((int) body.get("officeId"));

            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

}
