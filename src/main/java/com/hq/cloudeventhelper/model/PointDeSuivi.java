package com.hq.cloudeventhelper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointDeSuivi {

    String id;
    String userId;
    String latitude;
    String longitude;

}
