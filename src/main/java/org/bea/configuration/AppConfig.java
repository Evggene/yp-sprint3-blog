package org.bea.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.bea",
        excludeFilters = @ComponentScan.Filter(org.springframework.stereotype.Controller.class))
public class AppConfig {
    // Здесь могут быть бины сервисов, репозиториев и т.д.
}