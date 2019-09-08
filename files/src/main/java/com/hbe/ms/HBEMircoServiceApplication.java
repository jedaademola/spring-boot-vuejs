package com.hbe.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("file:${EXTERNAL_CONFIGURATION_FILES}/vlp-common.properties"),
        @PropertySource("file:${EXTERNAL_CONFIGURATION_FILES}/vlp1-application.properties")
})
public class HBEMircoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HBEMircoServiceApplication.class, args);
    }

}