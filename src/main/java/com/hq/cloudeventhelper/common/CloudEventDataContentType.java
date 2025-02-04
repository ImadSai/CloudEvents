package com.hq.cloudeventhelper.common;

/**
 * Enumération du type de contenu des données CloudEvents.
 */
public enum CloudEventDataContentType {

    APPLICATION_JSON("application/json");

    private final String value;

    CloudEventDataContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
