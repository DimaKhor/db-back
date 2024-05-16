package com.trafficpolice.dbback.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "organizations")
public class Organizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "area", length = 29, nullable = false)
    private String area;

    @Column(name = "street", length = 30)
    private String street;

    @Column(name = "building", length = 10)
    private String building;

    @Column(name = "head_lastname", length = 20, nullable = false)
    private String headLastName;

    @Column(name = "head_name", length = 20, nullable = false)
    private String headName;

    @Column(name = "head_fathername", length = 20)
    private String headFatherName;
}
