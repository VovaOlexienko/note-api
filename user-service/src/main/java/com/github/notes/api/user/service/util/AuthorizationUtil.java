package com.github.notes.api.user.service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Date;

@Component
@RefreshScope
public class AuthorizationUtil {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${jwt.token.validity}")
    private long jwtTokenValidity;

    @Value("${authorization.cookie.validity}")
    private int cookieValidity;

    public String generateJwtToken(String subject) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(subject))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + jwtTokenValidity))
                .compact();
    }

    public Cookie createAuthorizationCookie(String jwtToken) {
        Cookie authorizationCookie = new Cookie("Authorization", jwtToken);
        authorizationCookie.setMaxAge(cookieValidity);
        authorizationCookie.setHttpOnly(true);
        //TODO use cookie.setSecure(true) with ssl
        authorizationCookie.setPath("/");
        return authorizationCookie;
    }

    public Cookie deleteAuthorizationCookie() {
        Cookie authorizationCookie = new Cookie("Authorization", null);
        authorizationCookie.setMaxAge(0);
        authorizationCookie.setHttpOnly(true);
        authorizationCookie.setPath("/");
        return authorizationCookie;
    }
}