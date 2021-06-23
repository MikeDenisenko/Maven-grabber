package com.smartfoxpro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SourceProviderImpl {

    private static final Logger LOG = LogManager.getLogger(SourceProviderImpl.class);

    public void createRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);
        LOG.info(response.toString());
    }
}
