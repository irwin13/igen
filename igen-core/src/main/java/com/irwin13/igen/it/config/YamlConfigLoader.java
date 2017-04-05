package com.irwin13.igen.it.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by irwin on 24/03/17.
 */
public class YamlConfigLoader implements ConfigLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public IgenConfig loadConfig(String configFile) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        IgenConfig config;
        try {
            config = objectMapper.readValue(new File(configFile), IgenConfig.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error load configuration file : " + configFile);
        }
        logger.debug("config = {}", config);
        return config;
    }
}
