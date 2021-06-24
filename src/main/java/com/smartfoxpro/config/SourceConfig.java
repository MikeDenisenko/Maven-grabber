package com.smartfoxpro.config;


import com.smartfoxpro.SomeNewProvider;
import com.smartfoxpro.SourceProvider;
import com.smartfoxpro.SourceProviderImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("grabber-properties")
@Data
public class SourceConfig {

    @Value("${properties.numberOfThreads}")
    private int numberOfThreads;

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "solr")
    public SourceProvider sourceProvider() {
        return new SourceProviderImpl(numberOfThreads);
    }

    @Bean
    @ConditionalOnProperty(name = "service", havingValue = "somesource")
    public SourceProvider sourceAlternativeProvider()  {
        return new SomeNewProvider();
    }
}
