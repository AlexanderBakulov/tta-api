package com.bakulovas.tta.security;

import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    private final RoleRepository roleRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    public JwtProvider(RoleRepository roleRepository, OfficeRepository officeRepository) {
        this.roleRepository = roleRepository;
        this.officeRepository = officeRepository;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put("userId", user.getId());
        claims.put("isActive", user.isActive());
        claims.put("isTempPassword", user.isTempPassword());
        claims.put("office", user.getOffice().getName());
        claims.put("role", user.getRole().getName());

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
            Office office = officeRepository.getByName(String.valueOf(body.get("office")));
            Role role = roleRepository.getByName(String.valueOf(body.get("role")));

            User user = new User();
            user.setLogin(body.getSubject());
            user.setId((Integer) body.get("userId"));
            user.setActive((boolean) body.get("isActive"));
            user.setTempPassword((boolean) body.get("isTempPassword"));
            user.setOffice(office);
            user.setRole(role);
            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

}
