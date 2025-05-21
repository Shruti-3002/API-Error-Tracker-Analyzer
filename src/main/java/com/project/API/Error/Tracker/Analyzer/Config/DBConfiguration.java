package com.project.API.Error.Tracker.Analyzer.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.project.API.Error.Tracker.Analyzer.Repository")
@EnableTransactionManagement
public class DBConfiguration {
    // Additional custom configuration can be added here if needed
}
