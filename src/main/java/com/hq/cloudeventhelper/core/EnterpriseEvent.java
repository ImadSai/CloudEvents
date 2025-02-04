package com.hq.cloudeventhelper.core;

import com.hq.cloudeventhelper.common.CloudEventContentCategory;
import lombok.Builder;
import lombok.Data;

/**
 * Représente l’événement complet de l’entreprise combinant les HQ Specs et la partie CloudEvents.
 *
 * @param <T> le type du payload
 */
@Data
@Builder
public class EnterpriseEvent<T> {

    // Lettres Applicative
    private String applicationId;

    // Identifiant de l'utilisateur
    private String userId;

    // Identifiant de session
    private String sessionId;

    // Identifiant de la requête
    private String requestId;

    // Catégorie de contenu
    private CloudEventContentCategory contentCategory;

    // Partie CloudEvents de l'événement.
    private CloudEvent<T> cloudEvent;

}