package com.smartfoxpro;

import com.smartfoxpro.pojo.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class DataReceiver implements Callable<String> {

    private static final Logger LOG = LogManager.getLogger(DataReceiver.class);
    private String url;

    public DataReceiver(String urlString) {
        url = urlString;
    }

    @Override
    public String call() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForEntity(url, Response.class).getBody();

        return response.toString();
    }
}
