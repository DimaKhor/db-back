package com.trafficpolice.dbback.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "persons")
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lastname", length = 20, nullable = false)
    private String lastname;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "fathername", length = 20)
    private String fathername;

    @Column(name = "city", length = 20, nullable = false)
    private String city;

    @Column(name = "street", length = 30)
    private String street;

    @Column(name = "house", length = 10)
    private String house;

    @Column(name = "apartment")
    private Integer apartment;

    @OneToMany(mappedBy = "person")
    private List<TransportNumberDirectory> transportNumbers;
}
