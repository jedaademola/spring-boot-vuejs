package com.hbe.ms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = "classpath:build-number.properties")
public class AppBuildInfoConfig implements InfoContributor {


    //@Value("${build.number}")
    private String buildNumber;
	
	//@Value("${build.time}")
	private String buildTime;
	
	@Override
	public void contribute(Info.Builder builder) {

        builder.withDetail("Build Time", buildTime);
        builder.withDetail("Build Number", buildNumber);
			
	}

}