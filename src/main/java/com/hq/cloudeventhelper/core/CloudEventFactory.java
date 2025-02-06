package com.hq.cloudeventhelper.core;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud-event")
public class CloudEventFactory {

    private String source;

    // Instance statique pour un accès global
    private static CloudEventFactory INSTANCE;

    @PostConstruct
    public void init() {
        INSTANCE = this;
    }

    // Méthode statique pour accéder au builder
    public static <T> CloudEvent.CloudEventBuilder<T> builder() {
        return INSTANCE.builderInstance();
    }

    private <T> CloudEvent.CloudEventBuilder<T> builderInstance() {
        return CloudEvent.<T>builder()
                .source(source);
    }

    // Getters et setters pour les propriétés (obligatoires pour @ConfigurationProperties)
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

}
