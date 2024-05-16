package com.trafficpolice.dbback.entity;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "transportsdamagedinaccident")
public class TransportsDamagedInAccident {

    @EmbeddedId
    private TransportsDamagedInAccidentId id;

    @Column(name = "did_run_away", nullable = false)
    private boolean didRunAway;

    @Column(name = "found")
    private Boolean found;

    @ManyToOne
    @MapsId("accidentId")
    @JoinColumn(name = "accident_id", referencedColumnName = "id")
    private RoadAccidents roadAccident;

    @ManyToOne
    @MapsId("transportId")
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private TransportNumberDirectory transportNumberDirectory;
}
