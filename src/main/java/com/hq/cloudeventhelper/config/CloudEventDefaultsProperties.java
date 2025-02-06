package com.hq.cloudeventhelper.config;

import com.hq.cloudeventhelper.common.CloudEventDataContentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloudevent.defaults")
public class CloudEventDefaultsProperties {

    private String source = "changeme";
    private CloudEventDataContentType datacontenttype = CloudEventDataContentType.APPLICATION_JSON;

}

