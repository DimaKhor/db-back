package com.trafficpolice.dbback.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "inspection")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transport_id", referencedColumnName = "id", nullable = false)
    private TransportNumberDirectory transport;

    @Column(name = "payment_for_liter", nullable = false)
    private int paymentForLiter;

    @Column(name = "periodicity_in_months", nullable = false)
    private int periodicityInMonths;

    @Column(name = "last_time", nullable = false)
    private Date lastTime;
}
