package com.smartfoxpro;

import com.google.gson.Gson;
import com.smartfoxpro.pojo.Response;
import com.smartfoxpro.pojo.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SourceProviderImpl implements SourceProvider {

    private static final Logger LOG = LogManager.getLogger(SourceProviderImpl.class);

    private int numberOfThreads;

    private int startPosition = 2;

    public SourceProviderImpl(int threadsNum) {
        numberOfThreads = threadsNum;
    }

    public void createRequest(String searchElementTitle, String searchElementType) {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Callable<String>> callableTasks = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            String protocol = "http";
            String host = "search.maven.org";
            String source = "solrsearch";

            String url = "https://search.maven.org/solrsearch/select?q=" + searchElementType + ":" + searchElementTitle
                    + "*+AND+p:jar&start=" + startPosition + "&rows=1&wt=json";

            Callable<String> callableTask = new DataReceiver(url);
            callableTasks.add(callableTask);
            startPosition++;
        }

        try {
            List<Future<String>> futures = executor.invokeAll(callableTasks);

            for(Future<String> future : futures) {
                Gson gson = new Gson();
                Response response = gson.fromJson(future.get(), Response.class);
                LOG.info(response);
            }
        } catch(Exception ex) {
            LOG.info(ex.getMessage());
        }
    }
}
