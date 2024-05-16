package com.trafficpolice.dbback.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "hijackings")
public class Hijackings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transport_id", referencedColumnName = "id", nullable = false)
    private TransportNumberDirectory transport;

    @ManyToOne
    @JoinColumn(name = "result_id", referencedColumnName = "id", nullable = false)
    private HijackingsResult result;

    @Column(name = "signaling", nullable = false, length = 20)
    private String signaling;

    @Column(name = "date", nullable = false)
    private Date date;
}
