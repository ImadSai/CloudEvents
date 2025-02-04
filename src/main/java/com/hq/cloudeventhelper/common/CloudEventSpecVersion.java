package com.hq.cloudeventhelper.common;

/**
 * Enumération de la version de la spécification CloudEvents.
 */
public enum CloudEventSpecVersion {

    V1("1.0");

    private final String value;

    CloudEventSpecVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
