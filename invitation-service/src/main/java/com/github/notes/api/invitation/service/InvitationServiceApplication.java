package com.github.notes.api.invitation.service;

import com.github.notes.api.common.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@Import(CommonConfig.class)
@EnableDiscoveryClient
@SpringBootApplication
public class InvitationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvitationServiceApplication.class, args);
    }
}
