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
}
