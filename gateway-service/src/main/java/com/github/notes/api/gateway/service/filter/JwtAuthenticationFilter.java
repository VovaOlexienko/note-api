package com.github.notes.api.gateway.service.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RefreshScope
public class JwtAuthenticationFilter implements GatewayFilter {

    private static final List<String> AUTH_API_ENDPOINTS = List.of("/register", "/login");

    @Value("${jwt.secret.key}")
    private String jwtSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (isSecuredEndpoint(request)) {
            String jwtToken = getJwtTokenFromCookies(request);
            try {
                validateToken(jwtToken);
            } catch (Exception e) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            request.mutate().header("userId", String.valueOf(getClaims(jwtToken).get("sub"))).build();
        }
        return chain.filter(exchange);
    }

    private boolean isSecuredEndpoint(ServerHttpRequest request) {
        return AUTH_API_ENDPOINTS.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

    private String getJwtTokenFromCookies(ServerHttpRequest request) {
        return Optional.ofNullable(request.getCookies().getFirst("Authorization"))
                .map(HttpCookie::getValue)
                .orElse(null);
    }

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
}