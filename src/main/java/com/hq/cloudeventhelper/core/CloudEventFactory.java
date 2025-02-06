package com.hq.cloudeventhelper.core;

import com.hq.cloudeventhelper.config.CloudEventDefaultsProperties;

public class CloudEventFactory {

    private final CloudEventDefaultsProperties props;

    public CloudEventFactory(CloudEventDefaultsProperties props) {
        this.props = props;
    }

    public <T> CloudEvent.CloudEventBuilder<T> builder() {
        return CloudEvent.<T>builder()
                .source(props.getSource());
    }

}
