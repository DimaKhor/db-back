package com.trafficpolice.dbback.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class HijackingsDTO {
    private int id;
    private int transportId;
    private int resultId;
    private String signaling;
    private Date date;
}
