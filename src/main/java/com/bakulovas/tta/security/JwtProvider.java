package com.bakulovas.tta.security;

import com.bakulovas.tta.config.ServerConfig;
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
import org.springframework.stereotype.Component;

@Component
@Log
public class JwtProvider {

    private final RoleRepository roleRepository;
    private final OfficeRepository officeRepository;
    private final ServerConfig serverConfig;

    @Autowired
    public JwtProvider(RoleRepository roleRepository, OfficeRepository officeRepository, ServerConfig serverConfig) {
        this.roleRepository = roleRepository;
        this.officeRepository = officeRepository;
        this.serverConfig = serverConfig;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());
        claims.put("userId", user.getId());
        claims.put("office", user.getOffice().getName());
        claims.put("role", user.getRole().getName());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, serverConfig.getJwtSecret())
                .compact();
    }

    public User getUserFromToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(serverConfig.getJwtSecret())
                    .parseClaimsJws(token)
                    .getBody();

            Office office = officeRepository.getByName(String.valueOf(body.get("office")));
            Role role = roleRepository.getByName(String.valueOf(body.get("role")));

            User user = new User();
            user.setLogin(body.getSubject());
            user.setId((Integer) body.get("userId"));
            user.setOffice(office);
            user.setRole(role);

            log.info("USER_FROM_TOKEN " + user.getLogin() + user.getRole().getName() + user.getOffice().getName());
            return user;

        } catch (JwtException | ClassCastException | NullPointerException e) {
            log.info("USER_FROM_TOKEN IS NULL");
            return null;
        }
    }

}
