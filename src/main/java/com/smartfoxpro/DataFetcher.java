package com.smartfoxpro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataFetcher {

    private static final Logger LOG = LogManager.getLogger();
    SourceProvider sourceProvider;

    @Autowired
    DataFetcher(SourceProvider sourceProvider) {
        this.sourceProvider = sourceProvider;
    }

    public void fetchData(String url) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);
            LOG.info(response.toString());
        });
    }
}
