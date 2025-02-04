package com.hq.cloudeventhelper.common;

/**
 * Enumération de la catégorie de contenu d’un événement CloudEvent.
 */
public enum CloudEventContentCategory {

    USER_DATA("user-data"),
    APPLICATION_DATA("application-data"),
    ANALYTICS_DATA("analytics-data");

    private final String value;

    CloudEventContentCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
