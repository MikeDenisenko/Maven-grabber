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
    SourceProvider sourceProvider;
    private static final Logger LOG = LogManager.getLogger(Main.class);
    private static final Option GROUP_ID = new Option("g", "groupId", false, "groupId");
    private static final Option ARTIFACT_ID = new Option("a", "artifactId", false, "groupId");

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        CommandLineParser clp = new DefaultParser();
        Options options = new Options();
        options.addOption(GROUP_ID);
        options.addOption(ARTIFACT_ID);
        System.out.println(Arrays.toString(args));

        try{
            CommandLine cl = clp.parse(options, args);
            String searchElementTitle = args[1];
            String searchElementType = "";

            if (cl.hasOption(GROUP_ID.getLongOpt())||cl.hasOption(GROUP_ID.getOpt())) {
                searchElementType = "g";
            }
            else if (cl.hasOption(ARTIFACT_ID.getLongOpt())||cl.hasOption(ARTIFACT_ID.getOpt())){
                searchElementType = "a";
            }
            sourceProvider.createRequest(searchElementTitle, searchElementType);
        } catch(ParseException ex) {
            LOG.error(ex.getMessage());
        }
    }
}