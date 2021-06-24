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
    SourceProviderImpl sourceProvider;

    @Autowired
    DataFetcher(SourceProviderImpl sourceProvider) {
        this.sourceProvider = sourceProvider;
    }

    public void fetchData(String url) {

    }
}
