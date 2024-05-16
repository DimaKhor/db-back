package com.trafficpolice.dbback.dto;

import lombok.*;

@Getter
@Setter
public class RoadAccidentsDTO {
    private int id;
    private String date;
    private String place;
    private String brief;
    private int victimsNumber;
    private int damagePrice;
    private String reason;
    private String roadConditions;
    private int typeId;
}
