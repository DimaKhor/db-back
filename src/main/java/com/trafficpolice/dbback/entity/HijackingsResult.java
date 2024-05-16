package com.trafficpolice.dbback.entity;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "hijackingsresult")
public class HijackingsResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result_name", nullable = false, length = 20)
    private String resultName;
}
