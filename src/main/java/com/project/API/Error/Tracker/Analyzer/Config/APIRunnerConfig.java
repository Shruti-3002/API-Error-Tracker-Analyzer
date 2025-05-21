package com.project.API.Error.Tracker.Analyzer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class APIRunnerConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
