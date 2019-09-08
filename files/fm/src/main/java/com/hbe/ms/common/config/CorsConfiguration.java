package com.hbe.ms.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class CorsConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                // .allowedOrigins("http://localhost:8080/", "http://localhost:8081") //FOR SPECIFIC Domains
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*")
                //.allowedHeaders("X-Auth-Token", "Content-Type") //FOR SPECIFIC headers
                //.exposedHeaders("custom-header1", "custom-header2") //FOR SPECIFIC headers
                .allowCredentials(false)
                .maxAge(4800);
    }

}
