package com.example.testcontainers.config;


import com.example.testcontainers.sevice.DevProfile;
import com.example.testcontainers.sevice.ProductionProfile;
import com.example.testcontainers.sevice.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false", matchIfMissing = false)
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
