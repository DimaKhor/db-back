package com.trafficpolice.dbback.entity;

import lombok.*;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TransportsDamagedInAccidentId implements Serializable {
    private int accidentId;
    private int transportId;

    public TransportsDamagedInAccidentId() {
    }

    public TransportsDamagedInAccidentId(int accidentId, int transportId) {
        this.accidentId = accidentId;
        this.transportId = transportId;
    }
}
