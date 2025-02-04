package com.hq.cloudeventhelper.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hq.cloudeventhelper.common.CloudEventDataContentType;
import com.hq.cloudeventhelper.common.CloudEventSpecVersion;
import com.hq.cloudeventhelper.common.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Représente la partie CloudEvents d’un événement.
 *
 * @param <T> le type du payload
 */
@Data
@Builder
public class CloudEvent<T> {

    // Identifiant généré automatiquement.
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    // Date et heure de création générées automatiquement.
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date time = new Date();

    // Version de la spécification CloudEvents.
    private CloudEventSpecVersion specversion;

    // Type d'événement
    private EventType type;

    // Source de l'événement (exemple : URL).
    private String source;

    // Identifiant de l'élément concerné (ex. l'ID de la facture).
    private String subject;

    // Type de contenu des données.
    private CloudEventDataContentType datacontenttype;

    // Payload générique.
    private T data;

}