package com.github.notes.api.service.discovery.server;

import com.github.notes.api.common.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Import;

@Import(CommonConfig.class)
@SpringBootApplication
@EnableConfigServer
@EnableEurekaServer
public class ServiceDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryServerApplication.class, args);
    }
}

