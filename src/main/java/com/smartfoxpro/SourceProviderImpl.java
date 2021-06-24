package com.smartfoxpro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SourceProviderImpl implements SourceProvider {

    private static final Logger LOG = LogManager.getLogger(SourceProviderImpl.class);

    private int numberOfThreads;

    public SourceProviderImpl(int threadsNum) {
        numberOfThreads = threadsNum;
    }

    public void createRequest(String url) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);
            LOG.info(response.toString());
        });

    }
}
