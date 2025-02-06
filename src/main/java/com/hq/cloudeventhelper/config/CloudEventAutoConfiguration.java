package com.hq.cloudeventhelper.config;

import com.hq.cloudeventhelper.core.CloudEventFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CloudEventDefaultsProperties.class)
public class CloudEventAutoConfiguration {

    @Bean
    public CloudEventFactory cloudEventFactory(CloudEventDefaultsProperties props) {
        return new CloudEventFactory(props);
    }

}
