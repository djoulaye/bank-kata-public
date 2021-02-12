package com.bellagio.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.bellagio.infrastructure.dao"}, considerNestedRepositories = true)
@EntityScan("com.bellagio.infrastructure.entity")
@ComponentScan("com.bellagio.infrastructure.repository")
public class JpaConfig {
}
