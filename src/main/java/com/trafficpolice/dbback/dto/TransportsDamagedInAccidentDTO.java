package com.trafficpolice.dbback.dto;

import lombok.*;

@Getter
@Setter
public class TransportsDamagedInAccidentDTO {
    private int accidentId;
    private int transportId;
    private boolean didRunAway;
    private Boolean found;
}
