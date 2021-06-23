package com.smartfoxpro.config;


import com.smartfoxpro.SourceProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class RequestConfig {

    @Value("${properties.numberOfThreads}")
    int numberOfThreads;

    @Value("${properties.sourceProvider}")
    String sourceProvider;

    @Bean(name = "emailNotification")
    @ConditionalOnProperty(prefix = "notification", name = "service")
    public SourceProviderImpl sourceProvider(    ) {
        return new SourceProviderImpl();
    }

    @Bean(name = "emailNotification")
    @ConditionalOnProperty(prefix = "notification", name = "service")
    public SourceProviderImpl sourceAlternativeProvider()  {
        return new SourceProviderImpl();
    }

}
