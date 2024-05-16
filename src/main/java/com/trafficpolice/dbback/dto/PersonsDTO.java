package com.trafficpolice.dbback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonsDTO {
    private int id;
    private String lastname;
    private String name;
    private String fathername;
    private String city;
    private String street;
    private String house;
    private Integer apartment;

    public PersonsDTO() {
    }

    public PersonsDTO(int id, String lastname, String name, String fathername, String city, String street, String house, Integer apartment) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.fathername = fathername;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }
}
