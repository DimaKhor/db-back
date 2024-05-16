package com.trafficpolice.dbback.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class InspectionDTO {
    private int id;
    private int transportId;
    private int paymentForLiter;
    private int periodicityInMonths;
    private Date lastTime;
}
