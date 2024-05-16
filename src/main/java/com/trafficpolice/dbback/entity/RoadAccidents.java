package com.trafficpolice.dbback.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "roadaccidents")
public class RoadAccidents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "brief", nullable = false)
    private String brief;

    @Column(name = "victims_number", nullable = false)
    private int victimsNumber;

    @Column(name = "damage_price", nullable = false)
    private int damagePrice;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "road_conditions", nullable = false)
    private String roadConditions;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private AccidentTypes accidentType;
}
