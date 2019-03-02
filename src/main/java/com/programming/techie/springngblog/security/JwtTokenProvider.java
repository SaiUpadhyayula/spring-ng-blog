package com.programming.techie.springngblog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    private Key key;

    @Value("${jwt.token.validity}")
    private long jwtTokenValidity;

    @PostConstruct
    public void init() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Authentication authenticate) {
        User user = (User) authenticate.getPrincipal();

        long now = new Date().getTime();

        Date validity = new Date(now + this.jwtTokenValidity);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    boolean validateToken(String authToken) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
        return true;
    }
}
