package com.smartfoxpro;

import com.smartfoxpro.config.SourceConfig;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(SourceConfig.class)
public class Main implements CommandLineRunner {

    @Autowired
    SourceProviderImpl sourceProvider;
    private static final Logger LOG = LogManager.getLogger(Main.class);
    private static final Option GROUP_ID = new Option("g", "groupId", false, "groupId");
    private static final Option ARTIFACT_ID = new Option("a", "artifactId", false, "groupId");

    public static void main(String[] args) {

       SpringApplication.run(Main.class, args);

    }

    @Bean
    ApplicationRunner applicationRunner(SourceConfig sourceConfig) {
        return args -> {
            System.out.println(sourceConfig);
        };
    }

    @Bean
    DataFetcher dataFetcher(SourceProviderImpl sourceProvider) {
        return new DataFetcher(sourceProvider);
    }

    @Override
    public void run(String... args) throws Exception {


        SourceConfig sourceConfig = new SourceConfig();
        CommandLineParser clp = new DefaultParser();
        Options options = new Options();
        options.addOption(GROUP_ID);
        options.addOption(ARTIFACT_ID);
        System.out.println(Arrays.toString(args));

        try{
            CommandLine cl = clp.parse(options, args);
            String url = "";
            if (cl.hasOption(GROUP_ID.getLongOpt())) {
                url = "https://search.maven.org/solrsearch/select?q=g:" + args[1] + "*+AND+p:jar&rows=20&wt=json";

            }
            else if (cl.hasOption(ARTIFACT_ID.getLongOpt())){
                url = "https://search.maven.org/solrsearch/select?q=a:" + args[1] + "*+AND+p:\"jar\"&rows=20&wt=json";
            }

            sourceProvider.createRequest(url);

        } catch(ParseException ex) {
            LOG.error(ex.getMessage());
        }
    }
}