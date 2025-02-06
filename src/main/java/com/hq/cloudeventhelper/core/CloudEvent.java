package com.hq.cloudeventhelper.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hq.cloudeventhelper.common.CloudEventContentCategory;
import com.hq.cloudeventhelper.common.CloudEventDataContentType;
import com.hq.cloudeventhelper.common.CloudEventSpecVersion;
import com.hq.cloudeventhelper.common.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Représente la partie CloudEvents d’un événement.
 *
 * @param <T> le type du payload
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloudEvent<T> {

    //*******************************//
    //****** Enterprise Events ******//
    //*******************************//

    private String userId;

    private String sessionId;

    private String requestId;

    private CloudEventContentCategory contentCategory;

    //*******************************//
    //********* Cloud Events ********//
    //*******************************//

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date time = new Date();

    @Builder.Default
    private CloudEventSpecVersion specversion = CloudEventSpecVersion.V1;

    private EventType type;

    private String source;

    private String subject;

    @Builder.Default
    private CloudEventDataContentType datacontenttype = CloudEventDataContentType.APPLICATION_JSON;

    private T data;

}