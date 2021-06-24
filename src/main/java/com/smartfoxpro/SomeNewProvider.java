package com.smartfoxpro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SomeNewProvider implements SourceProvider{

    private static final Logger LOG = LogManager.getLogger(SomeNewProvider.class);

    @Override
    public void createRequest(String url) {
        LOG.info("Creating request with alternative source");
    }
}
