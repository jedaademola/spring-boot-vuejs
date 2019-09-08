package com.hbe.ms.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${resttemplate.timeout}")
    private int restTemplateTimeout;  //timeout in milliseconds

    @Value("${resttemplate.connection.timeout}")
    private int restTemplateConnectionTimeout; //connection timeout in milliseconds
    

    @Bean
	public RestTemplate restTemplate() {
    	SimpleClientHttpRequestFactory scfactory= new SimpleClientHttpRequestFactory();
    	scfactory.setConnectTimeout(restTemplateConnectionTimeout);   
    	scfactory.setReadTimeout(restTemplateTimeout);
		return new RestTemplate(scfactory);
	}

}
