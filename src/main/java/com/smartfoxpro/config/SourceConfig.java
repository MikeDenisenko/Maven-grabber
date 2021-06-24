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

    @Value("${properties.sourceProvider}")
    private String sourceProvider;

    @Bean(name = "solrsearch")
    @ConditionalOnProperty(prefix = "notification", name = "service")
    public SourceProvider sourceProvider() {
        return new SourceProviderImpl(numberOfThreads);
    }

//    @Bean(name = "somesource")
//    @ConditionalOnProperty(prefix = "notification", name = "service")
//    public SourceProvider sourceAlternativeProvider()  {
//        return new SomeNewProvider();
//    }
}
