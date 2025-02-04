package com.hq.cloudeventhelper.events.client.pointdesuivi;

import com.hq.cloudeventhelper.common.EventType;

/**
 * Enumération du type d’événement pour les points de suivi
 */
public enum PointDeSuiviEventType implements EventType {

    NOUVEAU_POINT_DE_SUIVI("com.hq.client.pointdesuivi.nouveau"),
    SUPPRESSION_POINT_DE_SUIVI("com.hq.client.pointdesuivi.suppression");

    private final String value;

    PointDeSuiviEventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}