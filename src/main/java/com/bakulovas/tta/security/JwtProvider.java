package com.bakulovas.tta.security;

import com.bakulovas.tta.entity.Office;
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

    public String generateToken(User u) {
        Claims claims = Jwts.claims().setSubject(u.getLogin());
        claims.put("userId", u.getId());
        claims.put("isActive", u.isActive());
        claims.put("isTempPassword", u.isTempPassword());
        claims.put("office", u.getOffice());
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

            User u = new User();
            u.setLogin(body.getSubject());
            u.setId(Integer.parseInt((String) body.get("userId")));
            u.setRole((String) body.get("role"));
            u.setActive((boolean) body.get("isActive"));
            u.setTempPassword((boolean) body.get("isTempPassword"));
            u.setOffice((Office) body.get("office"));

            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

}
