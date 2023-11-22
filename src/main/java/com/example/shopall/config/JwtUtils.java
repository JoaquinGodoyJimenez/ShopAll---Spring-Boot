package com.example.shopall.config;

import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.shopall.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@Configuration
public class JwtUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

    private static final String KEY = "mySecretKey";

    public String generateJwtToken(Authentication authentication, Usuario user){
    Instant now = Instant.now();
        return Jwts
                .builder()
                .setId(authentication.getName())
                .setSubject(user.getUsuario())
                .claim("authorities",
                        authentication.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        KEY.getBytes()).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        Claims body = Jwts.parser().setSigningKey(KEY.getBytes()).parseClaimsJws(token).getBody();
        LOG.info(body.getSubject());
        return body.getId();
    }

    public boolean validateJwtToken(String token) {
        Claims body = Jwts.parser().setSigningKey(KEY.getBytes()).parseClaimsJws(token).getBody();
        LOG.info(body.getSubject());
        return true;
    }
}
