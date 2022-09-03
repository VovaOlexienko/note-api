package com.github.notes.api.gateway.service.config;

import com.github.notes.api.common.config.CommonConfig;
import com.github.notes.api.gateway.service.filter.AccessDeniedFilter;
import com.github.notes.api.gateway.service.filter.JwtAuthenticationFilter;
import lombok.*;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonConfig.class)
@RequiredArgsConstructor
public class Config {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AccessDeniedFilter accessDeniedFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("notes-service", r -> r.path("/notes-service/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://notes-service"))
                .route("invitation-service", r -> r.path("/invitation-service/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://invitation-service"))
                .route("user-service", r -> r.path("/user-service/user/**").filters(f -> f.filter(accessDeniedFilter)).uri("lb://user-service"))
                .route("user-service", r -> r.path("/user-service/**").filters(f -> f.filter(jwtAuthenticationFilter)).uri("lb://user-service"))
                .build();
    }
}