package com.trafficpolice.dbback.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transportnumberdirectory")
public class TransportNumberDirectory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transport_type", referencedColumnName = "id", nullable = false)
    private TransportTypes transportType;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Persons person;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organizations organization;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brands brand;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    private Colors color;

    @Column(name = "issue_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(name = "engine_capacity", nullable = false)
    private int engineCapacity;

    @Column(name = "engine_id", unique = true, nullable = false, length = 20)
    private String engineId;

    @Column(name = "chassis_id", unique = true, nullable = false, length = 20)
    private String chassisId;

    @Column(name = "coachbuilder_id", unique = true, nullable = false, length = 20)
    private String coachbuilderId;

    @Column(name = "number", unique = true, nullable = false, length = 15)
    private String number;
}
