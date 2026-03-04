package com.ironhack.labpostman.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountFeatureConfig {

    @Bean
    @ConditionalOnProperty(name = "feature.discount.enabled", havingValue = "true")
    public EaryBirdDiscountService   earlyBirdDiscountService() {
        return new EaryBirdDiscountService();
    }
}
